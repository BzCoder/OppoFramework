package com.android.server.net;

import android.net.IpConfiguration;
import android.net.IpConfiguration.IpAssignment;
import android.net.IpConfiguration.ProxySettings;
import android.net.LinkAddress;
import android.net.NetworkUtils;
import android.net.ProxyInfo;
import android.net.RouteInfo;
import android.net.StaticIpConfiguration;
import android.util.Log;
import android.util.SparseArray;
import com.android.server.net.DelayedDiskWrite.Writer;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;

public class IpConfigStore {
    /* renamed from: -android-net-IpConfiguration$IpAssignmentSwitchesValues */
    private static final /* synthetic */ int[] f263-android-net-IpConfiguration$IpAssignmentSwitchesValues = null;
    /* renamed from: -android-net-IpConfiguration$ProxySettingsSwitchesValues */
    private static final /* synthetic */ int[] f264-android-net-IpConfiguration$ProxySettingsSwitchesValues = null;
    private static final boolean DBG = false;
    protected static final String DNS_KEY = "dns";
    protected static final String EOS = "eos";
    protected static final String EXCLUSION_LIST_KEY = "exclusionList";
    protected static final String GATEWAY_KEY = "gateway";
    protected static final String ID_KEY = "id";
    protected static final int IPCONFIG_FILE_VERSION = 2;
    protected static final String IP_ASSIGNMENT_KEY = "ipAssignment";
    protected static final String LINK_ADDRESS_KEY = "linkAddress";
    protected static final String PROXY_HOST_KEY = "proxyHost";
    protected static final String PROXY_PAC_FILE = "proxyPac";
    protected static final String PROXY_PORT_KEY = "proxyPort";
    protected static final String PROXY_SETTINGS_KEY = "proxySettings";
    private static final String TAG = "IpConfigStore";
    protected final DelayedDiskWrite mWriter;

    /* renamed from: -getandroid-net-IpConfiguration$IpAssignmentSwitchesValues */
    private static /* synthetic */ int[] m177-getandroid-net-IpConfiguration$IpAssignmentSwitchesValues() {
        if (f263-android-net-IpConfiguration$IpAssignmentSwitchesValues != null) {
            return f263-android-net-IpConfiguration$IpAssignmentSwitchesValues;
        }
        int[] iArr = new int[IpAssignment.values().length];
        try {
            iArr[IpAssignment.DHCP.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            iArr[IpAssignment.STATIC.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            iArr[IpAssignment.UNASSIGNED.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        f263-android-net-IpConfiguration$IpAssignmentSwitchesValues = iArr;
        return iArr;
    }

    /* renamed from: -getandroid-net-IpConfiguration$ProxySettingsSwitchesValues */
    private static /* synthetic */ int[] m178-getandroid-net-IpConfiguration$ProxySettingsSwitchesValues() {
        if (f264-android-net-IpConfiguration$ProxySettingsSwitchesValues != null) {
            return f264-android-net-IpConfiguration$ProxySettingsSwitchesValues;
        }
        int[] iArr = new int[ProxySettings.values().length];
        try {
            iArr[ProxySettings.NONE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            iArr[ProxySettings.PAC.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            iArr[ProxySettings.STATIC.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            iArr[ProxySettings.UNASSIGNED.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        f264-android-net-IpConfiguration$ProxySettingsSwitchesValues = iArr;
        return iArr;
    }

    public IpConfigStore(DelayedDiskWrite writer) {
        this.mWriter = writer;
    }

    public IpConfigStore() {
        this(new DelayedDiskWrite());
    }

    public static boolean writeConfig(DataOutputStream out, int configKey, IpConfiguration config) throws IOException {
        boolean written = false;
        try {
            switch (m177-getandroid-net-IpConfiguration$IpAssignmentSwitchesValues()[config.ipAssignment.ordinal()]) {
                case 1:
                    out.writeUTF(IP_ASSIGNMENT_KEY);
                    out.writeUTF(config.ipAssignment.toString());
                    written = true;
                    break;
                case 2:
                    out.writeUTF(IP_ASSIGNMENT_KEY);
                    out.writeUTF(config.ipAssignment.toString());
                    StaticIpConfiguration staticIpConfiguration = config.staticIpConfiguration;
                    if (staticIpConfiguration != null) {
                        if (staticIpConfiguration.ipAddress != null) {
                            LinkAddress ipAddress = staticIpConfiguration.ipAddress;
                            out.writeUTF(LINK_ADDRESS_KEY);
                            out.writeUTF(ipAddress.getAddress().getHostAddress());
                            out.writeInt(ipAddress.getPrefixLength());
                        }
                        if (staticIpConfiguration.gateway != null) {
                            out.writeUTF(GATEWAY_KEY);
                            out.writeInt(0);
                            out.writeInt(1);
                            out.writeUTF(staticIpConfiguration.gateway.getHostAddress());
                        }
                        for (InetAddress inetAddr : staticIpConfiguration.dnsServers) {
                            out.writeUTF(DNS_KEY);
                            out.writeUTF(inetAddr.getHostAddress());
                        }
                    }
                    written = true;
                    break;
                case 3:
                    break;
                default:
                    loge("Ignore invalid ip assignment while writing");
                    break;
            }
            switch (m178-getandroid-net-IpConfiguration$ProxySettingsSwitchesValues()[config.proxySettings.ordinal()]) {
                case 1:
                    out.writeUTF(PROXY_SETTINGS_KEY);
                    out.writeUTF(config.proxySettings.toString());
                    written = true;
                    break;
                case 2:
                    ProxyInfo proxyPacProperties = config.httpProxy;
                    out.writeUTF(PROXY_SETTINGS_KEY);
                    out.writeUTF(config.proxySettings.toString());
                    out.writeUTF(PROXY_PAC_FILE);
                    out.writeUTF(proxyPacProperties.getPacFileUrl().toString());
                    written = true;
                    break;
                case 3:
                    ProxyInfo proxyProperties = config.httpProxy;
                    String exclusionList = proxyProperties.getExclusionListAsString();
                    out.writeUTF(PROXY_SETTINGS_KEY);
                    out.writeUTF(config.proxySettings.toString());
                    out.writeUTF(PROXY_HOST_KEY);
                    out.writeUTF(proxyProperties.getHost());
                    out.writeUTF(PROXY_PORT_KEY);
                    out.writeInt(proxyProperties.getPort());
                    if (exclusionList != null) {
                        out.writeUTF(EXCLUSION_LIST_KEY);
                        out.writeUTF(exclusionList);
                    }
                    written = true;
                    break;
                case 4:
                    break;
                default:
                    loge("Ignore invalid proxy settings while writing");
                    break;
            }
            if (written) {
                out.writeUTF("id");
                out.writeInt(configKey);
            }
        } catch (NullPointerException e) {
            loge("Failure in writing " + config + e);
        }
        out.writeUTF(EOS);
        return written;
    }

    public void writeIpAndProxyConfigurations(String filePath, final SparseArray<IpConfiguration> networks) {
        this.mWriter.write(filePath, new Writer() {
            public void onWriteCalled(DataOutputStream out) throws IOException {
                out.writeInt(2);
                for (int i = 0; i < networks.size(); i++) {
                    IpConfigStore.writeConfig(out, networks.keyAt(i), (IpConfiguration) networks.valueAt(i));
                }
            }
        });
    }

    public static SparseArray<IpConfiguration> readIpAndProxyConfigurations(String filePath) {
        try {
            return readIpAndProxyConfigurations(new BufferedInputStream(new FileInputStream(filePath)));
        } catch (FileNotFoundException e) {
            loge("Error opening configuration file: " + e);
            return new SparseArray();
        }
    }

    public static SparseArray<IpConfiguration> readIpAndProxyConfigurations(InputStream inputStream) {
        IOException e;
        Throwable th;
        SparseArray<IpConfiguration> networks = new SparseArray();
        DataInputStream dataInputStream = null;
        try {
            DataInputStream in = new DataInputStream(inputStream);
            try {
                int version = in.readInt();
                if (version == 2 || version == 1) {
                    while (true) {
                        int id = -1;
                        IpAssignment ipAssignment = IpAssignment.DHCP;
                        ProxySettings proxySettings = ProxySettings.NONE;
                        StaticIpConfiguration staticIpConfiguration = new StaticIpConfiguration();
                        String proxyHost = null;
                        String pacFileUrl = null;
                        int proxyPort = -1;
                        String exclusionList = null;
                        while (true) {
                            String key = in.readUTF();
                            try {
                                if (key.equals("id")) {
                                    id = in.readInt();
                                } else if (key.equals(IP_ASSIGNMENT_KEY)) {
                                    ipAssignment = IpAssignment.valueOf(in.readUTF());
                                } else if (key.equals(LINK_ADDRESS_KEY)) {
                                    LinkAddress linkAddress = new LinkAddress(NetworkUtils.numericToInetAddress(in.readUTF()), in.readInt());
                                    if ((linkAddress.getAddress() instanceof Inet4Address) && staticIpConfiguration.ipAddress == null) {
                                        staticIpConfiguration.ipAddress = linkAddress;
                                    } else {
                                        loge("Non-IPv4 or duplicate address: " + linkAddress);
                                    }
                                } else if (key.equals(GATEWAY_KEY)) {
                                    LinkAddress linkAddress2 = null;
                                    InetAddress gateway = null;
                                    if (version == 1) {
                                        gateway = NetworkUtils.numericToInetAddress(in.readUTF());
                                        if (staticIpConfiguration.gateway == null) {
                                            staticIpConfiguration.gateway = gateway;
                                        } else {
                                            loge("Duplicate gateway: " + gateway.getHostAddress());
                                        }
                                    } else {
                                        if (in.readInt() == 1) {
                                            linkAddress2 = new LinkAddress(NetworkUtils.numericToInetAddress(in.readUTF()), in.readInt());
                                        }
                                        if (in.readInt() == 1) {
                                            gateway = NetworkUtils.numericToInetAddress(in.readUTF());
                                        }
                                        RouteInfo routeInfo = new RouteInfo(linkAddress2, gateway);
                                        if (routeInfo.isIPv4Default() && staticIpConfiguration.gateway == null) {
                                            staticIpConfiguration.gateway = gateway;
                                        } else {
                                            loge("Non-IPv4 default or duplicate route: " + routeInfo);
                                        }
                                    }
                                } else if (key.equals(DNS_KEY)) {
                                    staticIpConfiguration.dnsServers.add(NetworkUtils.numericToInetAddress(in.readUTF()));
                                } else if (key.equals(PROXY_SETTINGS_KEY)) {
                                    proxySettings = ProxySettings.valueOf(in.readUTF());
                                } else if (key.equals(PROXY_HOST_KEY)) {
                                    proxyHost = in.readUTF();
                                } else if (key.equals(PROXY_PORT_KEY)) {
                                    proxyPort = in.readInt();
                                } else if (key.equals(PROXY_PAC_FILE)) {
                                    pacFileUrl = in.readUTF();
                                } else if (key.equals(EXCLUSION_LIST_KEY)) {
                                    exclusionList = in.readUTF();
                                } else if (!key.equals(EOS)) {
                                    loge("Ignore unknown key " + key + "while reading");
                                } else if (id != -1) {
                                    IpConfiguration config = new IpConfiguration();
                                    networks.put(id, config);
                                    switch (m177-getandroid-net-IpConfiguration$IpAssignmentSwitchesValues()[ipAssignment.ordinal()]) {
                                        case 1:
                                            config.ipAssignment = ipAssignment;
                                            break;
                                        case 2:
                                            config.staticIpConfiguration = staticIpConfiguration;
                                            config.ipAssignment = ipAssignment;
                                            break;
                                        case 3:
                                            loge("BUG: Found UNASSIGNED IP on file, use DHCP");
                                            config.ipAssignment = IpAssignment.DHCP;
                                            break;
                                        default:
                                            loge("Ignore invalid ip assignment while reading.");
                                            config.ipAssignment = IpAssignment.UNASSIGNED;
                                            break;
                                    }
                                    ProxyInfo proxyInfo;
                                    switch (m178-getandroid-net-IpConfiguration$ProxySettingsSwitchesValues()[proxySettings.ordinal()]) {
                                        case 1:
                                            config.proxySettings = proxySettings;
                                            break;
                                        case 2:
                                            proxyInfo = new ProxyInfo(pacFileUrl);
                                            config.proxySettings = proxySettings;
                                            config.httpProxy = proxyInfo;
                                            break;
                                        case 3:
                                            proxyInfo = new ProxyInfo(proxyHost, proxyPort, exclusionList);
                                            config.proxySettings = proxySettings;
                                            config.httpProxy = proxyInfo;
                                            break;
                                        case 4:
                                            loge("BUG: Found UNASSIGNED proxy on file, use NONE");
                                            config.proxySettings = ProxySettings.NONE;
                                            break;
                                        default:
                                            loge("Ignore invalid proxy settings while reading");
                                            config.proxySettings = ProxySettings.UNASSIGNED;
                                            break;
                                    }
                                }
                            } catch (IllegalArgumentException e2) {
                                loge("Ignore invalid address while reading" + e2);
                            }
                        }
                    }
                } else {
                    loge("Bad version on IP configuration file, ignore read");
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Exception e3) {
                        }
                    }
                    return null;
                }
            } catch (EOFException e4) {
                dataInputStream = in;
            } catch (IOException e5) {
                e = e5;
                dataInputStream = in;
            } catch (Throwable th2) {
                th = th2;
                dataInputStream = in;
            }
        } catch (EOFException e6) {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (Exception e7) {
                }
            }
            return networks;
        } catch (IOException e8) {
            e = e8;
            try {
                loge("Error parsing configuration: " + e);
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (Exception e9) {
                    }
                }
                return networks;
            } catch (Throwable th3) {
                th = th3;
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (Exception e10) {
                    }
                }
                throw th;
            }
        }
    }

    protected static void loge(String s) {
        Log.e(TAG, s);
    }

    protected static void log(String s) {
        Log.d(TAG, s);
    }
}
