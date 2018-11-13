package java.net;

import android.system.GaiException;
import android.system.OsConstants;
import android.system.StructAddrinfo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream.PutField;
import java.io.ObjectStreamException;
import java.io.ObjectStreamField;
import java.io.Serializable;
import libcore.io.Libcore;
import sun.net.spi.nameservice.NameService;
import sun.net.util.IPAddressUtil;

/*  JADX ERROR: NullPointerException in pass: ReSugarCode
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
    	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
    	at java.lang.Iterable.forEach(Iterable.java:75)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
    	at jadx.core.ProcessClass.process(ProcessClass.java:37)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ExtractFieldInit.checkStaticFieldsInit(ExtractFieldInit.java:58)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
    	at java.lang.Iterable.forEach(Iterable.java:75)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
    	at jadx.core.ProcessClass.process(ProcessClass.java:37)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public class InetAddress implements Serializable {
    private static final ClassLoader BOOT_CLASSLOADER = null;
    static final int NETID_UNSET = 0;
    static final InetAddressImpl impl = null;
    private static final NameService nameService = null;
    private static final ObjectStreamField[] serialPersistentFields = null;
    private static final long serialVersionUID = 3286316764910316507L;
    private transient String canonicalHostName;
    transient InetAddressHolder holder;

    static class InetAddressHolder {
        int address;
        int family;
        String hostName;

        InetAddressHolder() {
        }

        InetAddressHolder(String hostName, int address, int family) {
            this.hostName = hostName;
            this.address = address;
            this.family = family;
        }

        String getHostName() {
            return this.hostName;
        }

        int getAddress() {
            return this.address;
        }

        int getFamily() {
            return this.family;
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e9 in method: java.net.InetAddress.<clinit>():void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e9
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 9 more
        */
    static {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e9 in method: java.net.InetAddress.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.InetAddress.<clinit>():void");
    }

    InetAddressHolder holder() {
        return this.holder;
    }

    InetAddress() {
        this.canonicalHostName = null;
        this.holder = new InetAddressHolder();
    }

    private Object readResolve() throws ObjectStreamException {
        return new Inet4Address(holder().getHostName(), holder().getAddress());
    }

    public boolean isMulticastAddress() {
        return false;
    }

    public boolean isAnyLocalAddress() {
        return false;
    }

    public boolean isLoopbackAddress() {
        return false;
    }

    public boolean isLinkLocalAddress() {
        return false;
    }

    public boolean isSiteLocalAddress() {
        return false;
    }

    public boolean isMCGlobal() {
        return false;
    }

    public boolean isMCNodeLocal() {
        return false;
    }

    public boolean isMCLinkLocal() {
        return false;
    }

    public boolean isMCSiteLocal() {
        return false;
    }

    public boolean isMCOrgLocal() {
        return false;
    }

    public boolean isReachable(int timeout) throws IOException {
        return isReachable(null, 0, timeout);
    }

    public boolean isReachable(NetworkInterface netif, int ttl, int timeout) throws IOException {
        if (ttl < 0) {
            throw new IllegalArgumentException("ttl can't be negative");
        } else if (timeout >= 0) {
            return impl.isReachable(this, timeout, netif, ttl);
        } else {
            throw new IllegalArgumentException("timeout can't be negative");
        }
    }

    public String getHostName() {
        if (holder().getHostName() == null) {
            holder().hostName = getHostFromNameService(this);
        }
        return holder().getHostName();
    }

    public String getCanonicalHostName() {
        if (this.canonicalHostName == null) {
            this.canonicalHostName = getHostFromNameService(this);
        }
        return this.canonicalHostName;
    }

    private static String getHostFromNameService(InetAddress addr) {
        String host;
        try {
            host = nameService.getHostByAddr(addr.getAddress());
            InetAddress[] arr = nameService.lookupAllHostAddr(host, 0);
            boolean ok = false;
            if (arr != null) {
                int i = 0;
                while (!ok && i < arr.length) {
                    ok = addr.equals(arr[i]);
                    i++;
                }
            }
            if (!ok) {
                return addr.getHostAddress();
            }
        } catch (UnknownHostException e) {
            host = addr.getHostAddress();
        }
        return host;
    }

    public byte[] getAddress() {
        return null;
    }

    public String getHostAddress() {
        return null;
    }

    public int hashCode() {
        return -1;
    }

    public boolean equals(Object obj) {
        return false;
    }

    public String toString() {
        String hostName = holder().getHostName();
        StringBuilder stringBuilder = new StringBuilder();
        if (hostName == null) {
            hostName = "";
        }
        return stringBuilder.append(hostName).append("/").append(getHostAddress()).toString();
    }

    public static InetAddress getByAddress(String host, byte[] addr) throws UnknownHostException {
        return getByAddress(host, addr, -1);
    }

    private static InetAddress getByAddress(String host, byte[] addr, int scopeId) throws UnknownHostException {
        if (host != null && host.length() > 0 && host.charAt(0) == '[' && host.charAt(host.length() - 1) == ']') {
            host = host.substring(1, host.length() - 1);
        }
        if (addr != null) {
            if (addr.length == 4) {
                return new Inet4Address(host, addr);
            }
            if (addr.length == 16) {
                byte[] newAddr = IPAddressUtil.convertFromIPv4MappedAddress(addr);
                if (newAddr != null) {
                    return new Inet4Address(host, newAddr);
                }
                return new Inet6Address(host, addr, scopeId);
            }
        }
        throw new UnknownHostException("addr is of illegal length");
    }

    public static InetAddress getByName(String host) throws UnknownHostException {
        return impl.lookupAllHostAddr(host, 0)[0];
    }

    public static InetAddress[] getAllByName(String host) throws UnknownHostException {
        return (InetAddress[]) impl.lookupAllHostAddr(host, 0).clone();
    }

    public static InetAddress getLoopbackAddress() {
        return impl.loopbackAddresses()[0];
    }

    public static InetAddress getByAddress(byte[] addr) throws UnknownHostException {
        return getByAddress(null, addr);
    }

    public static InetAddress getLocalHost() throws UnknownHostException {
        return impl.lookupAllHostAddr(Libcore.os.uname().nodename, 0)[0];
    }

    static InetAddress anyLocalAddress() {
        return impl.anyLocalAddress();
    }

    private void readObjectNoData(ObjectInputStream s) throws IOException, ClassNotFoundException {
        if (getClass().getClassLoader() != BOOT_CLASSLOADER) {
            throw new SecurityException("invalid address type");
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        if (getClass().getClassLoader() != BOOT_CLASSLOADER) {
            throw new SecurityException("invalid address type");
        }
        GetField gf = s.readFields();
        this.holder = new InetAddressHolder((String) gf.get("hostName", null), gf.get("address", 0), gf.get("family", 0));
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        if (getClass().getClassLoader() != BOOT_CLASSLOADER) {
            throw new SecurityException("invalid address type");
        }
        PutField pf = s.putFields();
        pf.put("hostName", holder().hostName);
        pf.put("address", holder().address);
        pf.put("family", holder().family);
        s.writeFields();
        s.flush();
    }

    public static boolean isNumeric(String address) {
        InetAddress inetAddress = parseNumericAddressNoThrow(address);
        if (inetAddress == null || disallowDeprecatedFormats(address, inetAddress) == null) {
            return false;
        }
        return true;
    }

    static InetAddress parseNumericAddressNoThrow(String address) {
        if (address.startsWith("[") && address.endsWith("]") && address.indexOf(58) != -1) {
            address = address.substring(1, address.length() - 1);
        }
        StructAddrinfo hints = new StructAddrinfo();
        hints.ai_flags = OsConstants.AI_NUMERICHOST;
        InetAddress[] addresses = null;
        try {
            addresses = Libcore.os.android_getaddrinfo(address, hints, 0);
        } catch (GaiException e) {
        }
        if (addresses != null) {
            return addresses[0];
        }
        return null;
    }

    static InetAddress disallowDeprecatedFormats(String address, InetAddress inetAddress) {
        if ((inetAddress instanceof Inet4Address) && address.indexOf(58) == -1) {
            return Libcore.os.inet_pton(OsConstants.AF_INET, address);
        }
        return inetAddress;
    }

    public static InetAddress parseNumericAddress(String numericAddress) {
        if (numericAddress == null || numericAddress.isEmpty()) {
            return Inet6Address.LOOPBACK;
        }
        InetAddress result = disallowDeprecatedFormats(numericAddress, parseNumericAddressNoThrow(numericAddress));
        if (result != null) {
            return result;
        }
        throw new IllegalArgumentException("Not a numeric address: " + numericAddress);
    }

    public static void clearDnsCache() {
        impl.clearAddressCache();
    }

    public static InetAddress getByNameOnNet(String host, int netId) throws UnknownHostException {
        System.out.println("[CDS][DNS] getByNameOnNet netId = " + netId);
        return impl.lookupAllHostAddr(host, netId)[0];
    }

    public static InetAddress[] getAllByNameOnNet(String host, int netId) throws UnknownHostException {
        System.out.println("[CDS][DNS] getAllByNameOnNet netId = " + netId);
        return (InetAddress[]) impl.lookupAllHostAddr(host, netId).clone();
    }

    static InetAddress[] getAllByName0(String authHost, boolean check) throws UnknownHostException {
        throw new UnsupportedOperationException();
    }

    String getHostName(boolean check) {
        throw new UnsupportedOperationException();
    }
}
