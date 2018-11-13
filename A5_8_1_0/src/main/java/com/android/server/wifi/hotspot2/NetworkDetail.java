package com.android.server.wifi.hotspot2;

import android.hardware.wifi.supplicant.V1_0.ISupplicantStaIfaceCallback.StatusCode;
import android.net.wifi.ScanResult.InformationElement;
import com.android.server.wifi.WifiConfigManager;
import com.android.server.wifi.hotspot2.anqp.ANQPElement;
import com.android.server.wifi.hotspot2.anqp.Constants;
import com.android.server.wifi.hotspot2.anqp.Constants.ANQPElementType;
import com.android.server.wifi.hotspot2.anqp.RawByteElement;
import com.android.server.wifi.hotspot2.anqp.eap.AuthParam;
import com.android.server.wifi.util.InformationElementUtil.BssLoad;
import com.android.server.wifi.util.InformationElementUtil.ExtendedCapabilities;
import com.android.server.wifi.util.InformationElementUtil.HtOperation;
import com.android.server.wifi.util.InformationElementUtil.Interworking;
import com.android.server.wifi.util.InformationElementUtil.RoamingConsortium;
import com.android.server.wifi.util.InformationElementUtil.SupportedRates;
import com.android.server.wifi.util.InformationElementUtil.TrafficIndicationMap;
import com.android.server.wifi.util.InformationElementUtil.VhtOperation;
import com.android.server.wifi.util.InformationElementUtil.Vsa;
import com.android.server.wifi.util.InformationElementUtil.WifiMode;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NetworkDetail {
    private static final boolean DBG = false;
    private static final String TAG = "NetworkDetail:";
    private final Map<ANQPElementType, ANQPElement> mANQPElements;
    private final int mAnqpDomainID;
    private final int mAnqpOICount;
    private final Ant mAnt;
    private final long mBSSID;
    private final int mCapacity;
    private final int mCenterfreq0;
    private final int mCenterfreq1;
    private final int mChannelUtilization;
    private final int mChannelWidth;
    private int mDtimInterval = -1;
    private final ExtendedCapabilities mExtendedCapabilities;
    private final long mHESSID;
    private final HSRelease mHSRelease;
    private final boolean mInternet;
    private final boolean mIsHiddenSsid;
    private final int mMaxRate;
    private final int mPrimaryFreq;
    private final long[] mRoamingConsortiums;
    private final String mSSID;
    private final int mStationCount;
    private final int mWifiMode;

    public enum Ant {
        Private,
        PrivateWithGuest,
        ChargeablePublic,
        FreePublic,
        Personal,
        EmergencyOnly,
        Resvd6,
        Resvd7,
        Resvd8,
        Resvd9,
        Resvd10,
        Resvd11,
        Resvd12,
        Resvd13,
        TestOrExperimental,
        Wildcard
    }

    public enum HSRelease {
        R1,
        R2,
        Unknown
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x008d A:{ExcHandler: java.lang.IllegalArgumentException (r9_0 'e' java.lang.RuntimeException), Splitter: B:5:0x0060, PHI: r22 } */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x008d A:{ExcHandler: java.lang.IllegalArgumentException (r9_0 'e' java.lang.RuntimeException), Splitter: B:5:0x0060, PHI: r22 } */
    /* JADX WARNING: Missing block: B:14:0x008d, code:
            r9 = move-exception;
     */
    /* JADX WARNING: Missing block: B:15:0x008e, code:
            android.util.Log.d(com.android.server.wifi.hotspot2.Utils.hs2LogTag(getClass()), "Caught " + r9);
     */
    /* JADX WARNING: Missing block: B:16:0x00af, code:
            if (r22 == null) goto L_0x00b1;
     */
    /* JADX WARNING: Missing block: B:18:0x00bd, code:
            throw new java.lang.IllegalArgumentException("Malformed IE string (no SSID)", r9);
     */
    /* JADX WARNING: Missing block: B:29:0x00ee, code:
            r10 = r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NetworkDetail(String bssid, InformationElement[] infoElements, List<String> list, int freq) {
        if (infoElements == null) {
            throw new IllegalArgumentException("Null information elements");
        }
        int i;
        int length;
        this.mBSSID = Utils.parseMac(bssid);
        String ssid = null;
        boolean isHiddenSsid = false;
        byte[] ssidOctets = null;
        BssLoad bssLoad = new BssLoad();
        Interworking interworking = new Interworking();
        RoamingConsortium roamingConsortium = new RoamingConsortium();
        Vsa vsa = new Vsa();
        HtOperation htOperation = new HtOperation();
        VhtOperation vhtOperation = new VhtOperation();
        ExtendedCapabilities extendedCapabilities = new ExtendedCapabilities();
        TrafficIndicationMap trafficIndicationMap = new TrafficIndicationMap();
        SupportedRates supportedRates = new SupportedRates();
        SupportedRates extendedSupportedRates = new SupportedRates();
        RuntimeException exception = null;
        ArrayList<Integer> iesFound = new ArrayList();
        try {
            for (InformationElement ie : infoElements) {
                iesFound.add(Integer.valueOf(ie.id));
                switch (ie.id) {
                    case 0:
                        ssidOctets = ie.bytes;
                        break;
                    case 1:
                        supportedRates.from(ie);
                        break;
                    case 5:
                        trafficIndicationMap.from(ie);
                        break;
                    case 11:
                        bssLoad.from(ie);
                        break;
                    case 50:
                        extendedSupportedRates.from(ie);
                        break;
                    case 61:
                        htOperation.from(ie);
                        break;
                    case StatusCode.AUTHORIZATION_DEENABLED /*107*/:
                        interworking.from(ie);
                        break;
                    case 111:
                        roamingConsortium.from(ie);
                        break;
                    case 127:
                        extendedCapabilities.from(ie);
                        break;
                    case WifiConfigManager.SCAN_CACHE_ENTRIES_MAX_SIZE /*192*/:
                        vhtOperation.from(ie);
                        break;
                    case AuthParam.PARAM_TYPE_VENDOR_SPECIFIC /*221*/:
                        vsa.from(ie);
                        break;
                    default:
                        break;
                }
            }
        } catch (RuntimeException e) {
        }
        if (ssidOctets != null) {
            try {
                ssid = StandardCharsets.UTF_8.newDecoder().decode(ByteBuffer.wrap(ssidOctets)).toString();
            } catch (CharacterCodingException e2) {
                ssid = null;
            }
            if (ssid == null) {
                if (!extendedCapabilities.isStrictUtf8() || exception == null) {
                    String str = new String(ssidOctets, StandardCharsets.ISO_8859_1);
                } else {
                    throw new IllegalArgumentException("Failed to decode SSID in dubious IE string");
                }
            }
            isHiddenSsid = true;
            i = 0;
            length = ssidOctets.length;
            while (i < length) {
                if (ssidOctets[i] != (byte) 0) {
                    isHiddenSsid = false;
                } else {
                    i++;
                }
            }
        }
        this.mSSID = ssid;
        this.mHESSID = interworking.hessid;
        this.mIsHiddenSsid = isHiddenSsid;
        this.mStationCount = bssLoad.stationCount;
        this.mChannelUtilization = bssLoad.channelUtilization;
        this.mCapacity = bssLoad.capacity;
        this.mAnt = interworking.ant;
        this.mInternet = interworking.internet;
        this.mHSRelease = vsa.hsRelease;
        this.mAnqpDomainID = vsa.anqpDomainID;
        this.mAnqpOICount = roamingConsortium.anqpOICount;
        this.mRoamingConsortiums = roamingConsortium.roamingConsortiums;
        this.mExtendedCapabilities = extendedCapabilities;
        this.mANQPElements = null;
        this.mPrimaryFreq = freq;
        if (vhtOperation.isValid()) {
            this.mChannelWidth = vhtOperation.getChannelWidth();
            this.mCenterfreq0 = vhtOperation.getCenterFreq0();
            this.mCenterfreq1 = vhtOperation.getCenterFreq1();
        } else {
            this.mChannelWidth = htOperation.getChannelWidth();
            this.mCenterfreq0 = htOperation.getCenterFreq0(this.mPrimaryFreq);
            this.mCenterfreq1 = 0;
        }
        if (trafficIndicationMap.isValid()) {
            this.mDtimInterval = trafficIndicationMap.mDtimPeriod;
        }
        int maxRateB = 0;
        if (extendedSupportedRates.isValid()) {
            maxRateB = ((Integer) extendedSupportedRates.mRates.get(extendedSupportedRates.mRates.size() - 1)).intValue();
        }
        if (supportedRates.isValid()) {
            int maxRateA = ((Integer) supportedRates.mRates.get(supportedRates.mRates.size() - 1)).intValue();
            if (maxRateA > maxRateB) {
                maxRateB = maxRateA;
            }
            this.mMaxRate = maxRateB;
            this.mWifiMode = WifiMode.determineMode(this.mPrimaryFreq, this.mMaxRate, vhtOperation.isValid(), iesFound.contains(Integer.valueOf(61)), iesFound.contains(Integer.valueOf(42)));
            return;
        }
        this.mWifiMode = 0;
        this.mMaxRate = 0;
    }

    private static ByteBuffer getAndAdvancePayload(ByteBuffer data, int plLength) {
        ByteBuffer payload = data.duplicate().order(data.order());
        payload.limit(payload.position() + plLength);
        data.position(data.position() + plLength);
        return payload;
    }

    private NetworkDetail(NetworkDetail base, Map<ANQPElementType, ANQPElement> anqpElements) {
        this.mSSID = base.mSSID;
        this.mIsHiddenSsid = base.mIsHiddenSsid;
        this.mBSSID = base.mBSSID;
        this.mHESSID = base.mHESSID;
        this.mStationCount = base.mStationCount;
        this.mChannelUtilization = base.mChannelUtilization;
        this.mCapacity = base.mCapacity;
        this.mAnt = base.mAnt;
        this.mInternet = base.mInternet;
        this.mHSRelease = base.mHSRelease;
        this.mAnqpDomainID = base.mAnqpDomainID;
        this.mAnqpOICount = base.mAnqpOICount;
        this.mRoamingConsortiums = base.mRoamingConsortiums;
        this.mExtendedCapabilities = new ExtendedCapabilities(base.mExtendedCapabilities);
        this.mANQPElements = anqpElements;
        this.mChannelWidth = base.mChannelWidth;
        this.mPrimaryFreq = base.mPrimaryFreq;
        this.mCenterfreq0 = base.mCenterfreq0;
        this.mCenterfreq1 = base.mCenterfreq1;
        this.mDtimInterval = base.mDtimInterval;
        this.mWifiMode = base.mWifiMode;
        this.mMaxRate = base.mMaxRate;
    }

    public NetworkDetail complete(Map<ANQPElementType, ANQPElement> anqpElements) {
        return new NetworkDetail(this, anqpElements);
    }

    public boolean queriable(List<ANQPElementType> queryElements) {
        if (this.mAnt == null) {
            return false;
        }
        if (Constants.hasBaseANQPElements(queryElements)) {
            return true;
        }
        return Constants.hasR2Elements(queryElements) && this.mHSRelease == HSRelease.R2;
    }

    public boolean has80211uInfo() {
        return (this.mAnt == null && this.mRoamingConsortiums == null && this.mHSRelease == null) ? false : true;
    }

    public boolean hasInterworking() {
        return this.mAnt != null;
    }

    public String getSSID() {
        return this.mSSID;
    }

    public String getTrimmedSSID() {
        if (this.mSSID != null) {
            for (int n = 0; n < this.mSSID.length(); n++) {
                if (this.mSSID.charAt(n) != 0) {
                    return this.mSSID;
                }
            }
        }
        return "";
    }

    public long getHESSID() {
        return this.mHESSID;
    }

    public long getBSSID() {
        return this.mBSSID;
    }

    public int getStationCount() {
        return this.mStationCount;
    }

    public int getChannelUtilization() {
        return this.mChannelUtilization;
    }

    public int getCapacity() {
        return this.mCapacity;
    }

    public boolean isInterworking() {
        return this.mAnt != null;
    }

    public Ant getAnt() {
        return this.mAnt;
    }

    public boolean isInternet() {
        return this.mInternet;
    }

    public HSRelease getHSRelease() {
        return this.mHSRelease;
    }

    public int getAnqpDomainID() {
        return this.mAnqpDomainID;
    }

    public byte[] getOsuProviders() {
        byte[] bArr = null;
        if (this.mANQPElements == null) {
            return null;
        }
        ANQPElement osuProviders = (ANQPElement) this.mANQPElements.get(ANQPElementType.HSOSUProviders);
        if (osuProviders != null) {
            bArr = ((RawByteElement) osuProviders).getPayload();
        }
        return bArr;
    }

    public int getAnqpOICount() {
        return this.mAnqpOICount;
    }

    public long[] getRoamingConsortiums() {
        return this.mRoamingConsortiums;
    }

    public Map<ANQPElementType, ANQPElement> getANQPElements() {
        return this.mANQPElements;
    }

    public int getChannelWidth() {
        return this.mChannelWidth;
    }

    public int getCenterfreq0() {
        return this.mCenterfreq0;
    }

    public int getCenterfreq1() {
        return this.mCenterfreq1;
    }

    public int getWifiMode() {
        return this.mWifiMode;
    }

    public int getDtimInterval() {
        return this.mDtimInterval;
    }

    public boolean is80211McResponderSupport() {
        return this.mExtendedCapabilities.is80211McRTTResponder();
    }

    public boolean isSSID_UTF8() {
        return this.mExtendedCapabilities.isStrictUtf8();
    }

    public boolean equals(Object thatObject) {
        boolean z = true;
        if (this == thatObject) {
            return true;
        }
        if (thatObject == null || getClass() != thatObject.getClass()) {
            return false;
        }
        NetworkDetail that = (NetworkDetail) thatObject;
        if (!(getSSID().equals(that.getSSID()) && getBSSID() == that.getBSSID())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((this.mSSID.hashCode() * 31) + ((int) (this.mBSSID >>> 32))) * 31) + ((int) this.mBSSID);
    }

    public String toString() {
        return String.format("NetworkInfo{SSID='%s', HESSID=%x, BSSID=%x, StationCount=%d, ChannelUtilization=%d, Capacity=%d, Ant=%s, Internet=%s, HSRelease=%s, AnqpDomainID=%d, AnqpOICount=%d, RoamingConsortiums=%s}", new Object[]{this.mSSID, Long.valueOf(this.mHESSID), Long.valueOf(this.mBSSID), Integer.valueOf(this.mStationCount), Integer.valueOf(this.mChannelUtilization), Integer.valueOf(this.mCapacity), this.mAnt, Boolean.valueOf(this.mInternet), this.mHSRelease, Integer.valueOf(this.mAnqpDomainID), Integer.valueOf(this.mAnqpOICount), Utils.roamingConsortiumsToString(this.mRoamingConsortiums)});
    }

    public String toKeyString() {
        if (this.mHESSID != 0) {
            return String.format("'%s':%012x (%012x)", new Object[]{this.mSSID, Long.valueOf(this.mBSSID), Long.valueOf(this.mHESSID)});
        }
        return String.format("'%s':%012x", new Object[]{this.mSSID, Long.valueOf(this.mBSSID)});
    }

    public String getBSSIDString() {
        return toMACString(this.mBSSID);
    }

    public boolean isBeaconFrame() {
        return this.mDtimInterval > 0;
    }

    public boolean isHiddenBeaconFrame() {
        return isBeaconFrame() ? this.mIsHiddenSsid : false;
    }

    public static String toMACString(long mac) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int n = 5; n >= 0; n--) {
            if (first) {
                first = false;
            } else {
                sb.append(':');
            }
            sb.append(String.format("%02x", new Object[]{Long.valueOf((mac >>> (n * 8)) & 255)}));
        }
        return sb.toString();
    }
}
