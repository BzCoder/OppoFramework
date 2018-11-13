package android.mtp;

/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.utils.BlockUtils.isAllBlocksEmpty(BlockUtils.java:546)
    	at jadx.core.dex.visitors.ExtractFieldInit.getConstructorsList(ExtractFieldInit.java:221)
    	at jadx.core.dex.visitors.ExtractFieldInit.moveCommonFieldsInit(ExtractFieldInit.java:121)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:46)
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
public final class MtpConstants {
    public static final int ASSOCIATION_TYPE_GENERIC_FOLDER = 1;
    public static final int DEVICE_PROPERTY_ARTIST = 20510;
    public static final int DEVICE_PROPERTY_BATTERY_LEVEL = 20481;
    public static final int DEVICE_PROPERTY_BURST_INTERVAL = 20505;
    public static final int DEVICE_PROPERTY_BURST_NUMBER = 20504;
    public static final int DEVICE_PROPERTY_CAPTURE_DELAY = 20498;
    public static final int DEVICE_PROPERTY_COMPRESSION_SETTING = 20484;
    public static final int DEVICE_PROPERTY_CONTRAST = 20500;
    public static final int DEVICE_PROPERTY_COPYRIGHT_INFO = 20511;
    public static final int DEVICE_PROPERTY_DATETIME = 20497;
    public static final int DEVICE_PROPERTY_DEVICE_FRIENDLY_NAME = 54274;
    public static final int DEVICE_PROPERTY_DEVICE_ICON = 54277;
    public static final int DEVICE_PROPERTY_DIGITAL_ZOOM = 20502;
    public static final int DEVICE_PROPERTY_EFFECT_MODE = 20503;
    public static final int DEVICE_PROPERTY_EXPOSURE_BIAS_COMPENSATION = 20496;
    public static final int DEVICE_PROPERTY_EXPOSURE_INDEX = 20495;
    public static final int DEVICE_PROPERTY_EXPOSURE_METERING_MODE = 20491;
    public static final int DEVICE_PROPERTY_EXPOSURE_PROGRAM_MODE = 20494;
    public static final int DEVICE_PROPERTY_EXPOSURE_TIME = 20493;
    public static final int DEVICE_PROPERTY_FLASH_MODE = 20492;
    public static final int DEVICE_PROPERTY_FOCAL_LENGTH = 20488;
    public static final int DEVICE_PROPERTY_FOCUS_DISTANCE = 20489;
    public static final int DEVICE_PROPERTY_FOCUS_METERING_MODE = 20508;
    public static final int DEVICE_PROPERTY_FOCUS_MODE = 20490;
    public static final int DEVICE_PROPERTY_FUNCTIONAL_MODE = 20482;
    public static final int DEVICE_PROPERTY_F_NUMBER = 20487;
    public static final int DEVICE_PROPERTY_IMAGE_SIZE = 20483;
    public static final int DEVICE_PROPERTY_PERCEIVED_DEVICE_TYPE = 54279;
    public static final int DEVICE_PROPERTY_PLAYBACK_CONTAINER_INDEX = 54290;
    public static final int DEVICE_PROPERTY_PLAYBACK_OBJECT = 54289;
    public static final int DEVICE_PROPERTY_PLAYBACK_RATE = 54288;
    public static final int DEVICE_PROPERTY_RGB_GAIN = 20486;
    public static final int DEVICE_PROPERTY_SESSION_INITIATOR_VERSION_INFO = 54278;
    public static final int DEVICE_PROPERTY_SHARPNESS = 20501;
    public static final int DEVICE_PROPERTY_STILL_CAPTURE_MODE = 20499;
    public static final int DEVICE_PROPERTY_SUPPORTED_FORMATS_ORDERED = 54276;
    public static final int DEVICE_PROPERTY_SYNCHRONIZATION_PARTNER = 54273;
    public static final int DEVICE_PROPERTY_TIMELAPSE_INTERVAL = 20507;
    public static final int DEVICE_PROPERTY_TIMELAPSE_NUMBER = 20506;
    public static final int DEVICE_PROPERTY_UNDEFINED = 20480;
    public static final int DEVICE_PROPERTY_UPLOAD_URL = 20509;
    public static final int DEVICE_PROPERTY_VOLUME = 54275;
    public static final int DEVICE_PROPERTY_WHITE_BALANCE = 20485;
    public static final int EVENT_CANCEL_TRANSACTION = 16385;
    public static final int EVENT_CAPTURE_COMPLETE = 16397;
    public static final int EVENT_DEVICE_INFO_CHANGED = 16392;
    public static final int EVENT_DEVICE_PROP_CHANGED = 16390;
    public static final int EVENT_DEVICE_RESET = 16395;
    public static final int EVENT_OBJECT_ADDED = 16386;
    public static final int EVENT_OBJECT_INFO_CHANGED = 16391;
    public static final int EVENT_OBJECT_PROP_CHANGED = 51201;
    public static final int EVENT_OBJECT_PROP_DESC_CHANGED = 51202;
    public static final int EVENT_OBJECT_REFERENCES_CHANGED = 51203;
    public static final int EVENT_OBJECT_REMOVED = 16387;
    public static final int EVENT_REQUEST_OBJECT_TRANSFER = 16393;
    public static final int EVENT_STORAGE_INFO_CHANGED = 16396;
    public static final int EVENT_STORE_ADDED = 16388;
    public static final int EVENT_STORE_FULL = 16394;
    public static final int EVENT_STORE_REMOVED = 16389;
    public static final int EVENT_UNDEFINED = 16384;
    public static final int EVENT_UNREPORTED_STATUS = 16398;
    public static final int FORMAT_3GP_CONTAINER = 47492;
    public static final int FORMAT_AAC = 47363;
    public static final int FORMAT_ABSTRACT_AUDIO_ALBUM = 47619;
    public static final int FORMAT_ABSTRACT_AUDIO_PLAYLIST = 47625;
    public static final int FORMAT_ABSTRACT_AV_PLAYLIST = 47621;
    public static final int FORMAT_ABSTRACT_DOCUMENT = 47745;
    public static final int FORMAT_ABSTRACT_IMAGE_ALBUM = 47618;
    public static final int FORMAT_ABSTRACT_MEDIACAST = 47627;
    public static final int FORMAT_ABSTRACT_MULTIMEDIA_ALBUM = 47617;
    public static final int FORMAT_ABSTRACT_VIDEO_ALBUM = 47620;
    public static final int FORMAT_ABSTRACT_VIDEO_PLAYLIST = 47626;
    public static final int FORMAT_AIFF = 12295;
    public static final int FORMAT_ASF = 12300;
    public static final int FORMAT_ASSOCIATION = 12289;
    public static final int FORMAT_ASX_PLAYLIST = 47635;
    public static final int FORMAT_AUDIBLE = 47364;
    public static final int FORMAT_AVI = 12298;
    public static final int FORMAT_BMP = 14340;
    public static final int FORMAT_DEFINED = 14336;
    public static final int FORMAT_DNG = 14353;
    public static final int FORMAT_DPOF = 12294;
    public static final int FORMAT_EXECUTABLE = 12291;
    public static final int FORMAT_EXIF_JPEG = 14337;
    public static final int FORMAT_FLAC = 47366;
    public static final int FORMAT_GIF = 14343;
    public static final int FORMAT_HTML = 12293;
    public static final int FORMAT_JFIF = 14344;
    public static final int FORMAT_JP2 = 14351;
    public static final int FORMAT_JPX = 14352;
    public static final int FORMAT_M3U_PLAYLIST = 47633;
    public static final int FORMAT_MP2 = 47491;
    public static final int FORMAT_MP3 = 12297;
    public static final int FORMAT_MP4_CONTAINER = 47490;
    public static final int FORMAT_MPEG = 12299;
    public static final int FORMAT_MPL_PLAYLIST = 47634;
    public static final int FORMAT_MS_EXCEL_SPREADSHEET = 47749;
    public static final int FORMAT_MS_POWERPOINT_PRESENTATION = 47750;
    public static final int FORMAT_MS_WORD_DOCUMENT = 47747;
    public static final int FORMAT_OGG = 47362;
    public static final int FORMAT_PICT = 14346;
    public static final int FORMAT_PLS_PLAYLIST = 47636;
    public static final int FORMAT_PNG = 14347;
    public static final int FORMAT_SCRIPT = 12290;
    public static final int FORMAT_TEXT = 12292;
    public static final int FORMAT_TIFF = 14349;
    public static final int FORMAT_TIFF_EP = 14338;
    public static final int FORMAT_UNDEFINED = 12288;
    public static final int FORMAT_UNDEFINED_AUDIO = 47360;
    public static final int FORMAT_UNDEFINED_COLLECTION = 47616;
    public static final int FORMAT_UNDEFINED_DOCUMENT = 47744;
    public static final int FORMAT_UNDEFINED_FIRMWARE = 47106;
    public static final int FORMAT_UNDEFINED_VIDEO = 47488;
    public static final int FORMAT_WAV = 12296;
    public static final int FORMAT_WINDOWS_IMAGE_FORMAT = 47233;
    public static final int FORMAT_WMA = 47361;
    public static final int FORMAT_WMV = 47489;
    public static final int FORMAT_WPL_PLAYLIST = 47632;
    public static final int FORMAT_XML_DOCUMENT = 47746;
    public static final int OPERATION_CLOSE_SESSION = 4099;
    public static final int OPERATION_COPY_OBJECT = 4122;
    public static final int OPERATION_DELETE_OBJECT = 4107;
    public static final int OPERATION_FORMAT_STORE = 4111;
    public static final int OPERATION_GET_DEVICE_INFO = 4097;
    public static final int OPERATION_GET_DEVICE_PROP_DESC = 4116;
    public static final int OPERATION_GET_DEVICE_PROP_VALUE = 4117;
    public static final int OPERATION_GET_NUM_OBJECTS = 4102;
    public static final int OPERATION_GET_OBJECT = 4105;
    public static final int OPERATION_GET_OBJECT_HANDLES = 4103;
    public static final int OPERATION_GET_OBJECT_INFO = 4104;
    public static final int OPERATION_GET_OBJECT_PROPS_SUPPORTED = 38913;
    public static final int OPERATION_GET_OBJECT_PROP_DESC = 38914;
    public static final int OPERATION_GET_OBJECT_PROP_VALUE = 38915;
    public static final int OPERATION_GET_OBJECT_REFERENCES = 38928;
    public static final int OPERATION_GET_PARTIAL_OBJECT = 4123;
    public static final int OPERATION_GET_PARTIAL_OBJECT_64 = 38337;
    public static final int OPERATION_GET_STORAGE_INFO = 4101;
    public static final int OPERATION_GET_STORAGE_I_DS = 4100;
    public static final int OPERATION_GET_THUMB = 4106;
    public static final int OPERATION_INITIATE_CAPTURE = 4110;
    public static final int OPERATION_INITIATE_OPEN_CAPTURE = 4124;
    public static final int OPERATION_MOVE_OBJECT = 4121;
    public static final int OPERATION_OPEN_SESSION = 4098;
    public static final int OPERATION_POWER_DOWN = 4115;
    public static final int OPERATION_RESET_DEVICE = 4112;
    public static final int OPERATION_RESET_DEVICE_PROP_VALUE = 4119;
    public static final int OPERATION_SELF_TEST = 4113;
    public static final int OPERATION_SEND_OBJECT = 4109;
    public static final int OPERATION_SEND_OBJECT_INFO = 4108;
    public static final int OPERATION_SET_DEVICE_PROP_VALUE = 4118;
    public static final int OPERATION_SET_OBJECT_PROP_VALUE = 38916;
    public static final int OPERATION_SET_OBJECT_PROTECTION = 4114;
    public static final int OPERATION_SET_OBJECT_REFERENCES = 38929;
    public static final int OPERATION_SKIP = 38944;
    public static final int OPERATION_TERMINATE_OPEN_CAPTURE = 4120;
    public static final int PROPERTY_ALBUM_ARTIST = 56475;
    public static final int PROPERTY_ALBUM_NAME = 56474;
    public static final int PROPERTY_ALLOWED_FOLDER_CONTENTS = 56332;
    public static final int PROPERTY_ARTIST = 56390;
    public static final int PROPERTY_ASSOCIATION_DESC = 56326;
    public static final int PROPERTY_ASSOCIATION_TYPE = 56325;
    public static final int PROPERTY_AUDIO_BITRATE = 56986;
    public static final int PROPERTY_AUDIO_BIT_DEPTH = 56981;
    public static final int PROPERTY_AUDIO_WAVE_CODEC = 56985;
    public static final int PROPERTY_BITRATE_TYPE = 56978;
    public static final int PROPERTY_BUFFER_SIZE = 56991;
    public static final int PROPERTY_COMPOSER = 56470;
    public static final int PROPERTY_COPYRIGHT_INFORMATION = 56395;
    public static final int PROPERTY_CORRUPT_UNPLAYABLE = 56400;
    public static final int PROPERTY_CREATED_BY = 56389;
    public static final int PROPERTY_CREDITS = 56461;
    public static final int PROPERTY_DATE_ADDED = 56398;
    public static final int PROPERTY_DATE_AUTHORED = 56391;
    public static final int PROPERTY_DATE_CREATED = 56328;
    public static final int PROPERTY_DATE_MODIFIED = 56329;
    public static final int PROPERTY_DESCRIPTION = 56392;
    public static final int PROPERTY_DISPLAY_NAME = 56544;
    public static final int PROPERTY_DRM_STATUS = 56477;
    public static final int PROPERTY_DURATION = 56457;
    public static final int PROPERTY_EFFECTIVE_RATING = 56471;
    public static final int PROPERTY_ENCODING_PROFILE = 56993;
    public static final int PROPERTY_ENCODING_QUALITY = 56992;
    public static final int PROPERTY_EXPOSURE_INDEX = 56534;
    public static final int PROPERTY_EXPOSURE_TIME = 56533;
    public static final int PROPERTY_FRAMES_PER_THOUSAND_SECONDS = 56989;
    public static final int PROPERTY_F_NUMBER = 56532;
    public static final int PROPERTY_GENRE = 56460;
    public static final int PROPERTY_HEIGHT = 56456;
    public static final int PROPERTY_HIDDEN = 56333;
    public static final int PROPERTY_IMAGE_BIT_DEPTH = 56531;
    public static final int PROPERTY_IS_COLOUR_CORRECTED = 56530;
    public static final int PROPERTY_IS_CROPPED = 56529;
    public static final int PROPERTY_KEYFRAME_DISTANCE = 56990;
    public static final int PROPERTY_KEYWORDS = 56330;
    public static final int PROPERTY_LANGUAGE_LOCALE = 56394;
    public static final int PROPERTY_LAST_ACCESSED = 56467;
    public static final int PROPERTY_LYRICS = 56462;
    public static final int PROPERTY_META_GENRE = 56469;
    public static final int PROPERTY_MOOD = 56476;
    public static final int PROPERTY_NAME = 56388;
    public static final int PROPERTY_NON_CONSUMABLE = 56399;
    public static final int PROPERTY_NUMBER_OF_CHANNELS = 56980;
    public static final int PROPERTY_OBJECT_FILE_NAME = 56327;
    public static final int PROPERTY_OBJECT_FORMAT = 56322;
    public static final int PROPERTY_OBJECT_SIZE = 56324;
    public static final int PROPERTY_ORIGINAL_RELEASE_DATE = 56473;
    public static final int PROPERTY_ORIGIN_LOCATION = 56397;
    public static final int PROPERTY_PARENTAL_RATING = 56468;
    public static final int PROPERTY_PARENT_OBJECT = 56331;
    public static final int PROPERTY_PERSISTENT_UID = 56385;
    public static final int PROPERTY_PRODUCED_BY = 56464;
    public static final int PROPERTY_PRODUCER_SERIAL_NUMBER = 56401;
    public static final int PROPERTY_PROPERTY_BAG = 56387;
    public static final int PROPERTY_PROTECTION_STATUS = 56323;
    public static final int PROPERTY_RATING = 56458;
    public static final int PROPERTY_REPRESENTATIVE_SAMPLE_DATA = 56454;
    public static final int PROPERTY_REPRESENTATIVE_SAMPLE_DURATION = 56453;
    public static final int PROPERTY_REPRESENTATIVE_SAMPLE_FORMAT = 56449;
    public static final int PROPERTY_REPRESENTATIVE_SAMPLE_HEIGHT = 56451;
    public static final int PROPERTY_REPRESENTATIVE_SAMPLE_SIZE = 56450;
    public static final int PROPERTY_REPRESENTATIVE_SAMPLE_WIDTH = 56452;
    public static final int PROPERTY_SAMPLE_RATE = 56979;
    public static final int PROPERTY_SCAN_TYPE = 56983;
    public static final int PROPERTY_SKIP_COUNT = 56466;
    public static final int PROPERTY_SOURCE = 56396;
    public static final int PROPERTY_STORAGE_ID = 56321;
    public static final int PROPERTY_SUBSCRIPTION_CONTENT_ID = 56463;
    public static final int PROPERTY_SUBTITLE = 56472;
    public static final int PROPERTY_SUB_DESCRIPTION = 56478;
    public static final int PROPERTY_SYNC_ID = 56386;
    public static final int PROPERTY_SYSTEM_OBJECT = 56334;
    public static final int PROPERTY_TOTAL_BITRATE = 56977;
    public static final int PROPERTY_TRACK = 56459;
    public static final int PROPERTY_URL_REFERENCE = 56393;
    public static final int PROPERTY_USE_COUNT = 56465;
    public static final int PROPERTY_VIDEO_BITRATE = 56988;
    public static final int PROPERTY_VIDEO_FOURCC_CODEC = 56987;
    public static final int PROPERTY_WIDTH = 56455;
    public static final int PROTECTION_STATUS_NONE = 0;
    public static final int PROTECTION_STATUS_NON_TRANSFERABLE_DATA = 32771;
    public static final int PROTECTION_STATUS_READ_ONLY = 32769;
    public static final int PROTECTION_STATUS_READ_ONLY_DATA = 32770;
    public static final int RESPONSE_ACCESS_DENIED = 8207;
    public static final int RESPONSE_CAPTURE_ALREADY_TERMINATED = 8216;
    public static final int RESPONSE_DEVICE_BUSY = 8217;
    public static final int RESPONSE_DEVICE_PROP_NOT_SUPPORTED = 8202;
    public static final int RESPONSE_GENERAL_ERROR = 8194;
    public static final int RESPONSE_GROUP_NOT_SUPPORTED = 43013;
    public static final int RESPONSE_INCOMPLETE_TRANSFER = 8199;
    public static final int RESPONSE_INVALID_CODE_FORMAT = 8214;
    public static final int RESPONSE_INVALID_DATASET = 43014;
    public static final int RESPONSE_INVALID_DEVICE_PROP_FORMAT = 8219;
    public static final int RESPONSE_INVALID_DEVICE_PROP_VALUE = 8220;
    public static final int RESPONSE_INVALID_OBJECT_FORMAT_CODE = 8203;
    public static final int RESPONSE_INVALID_OBJECT_HANDLE = 8201;
    public static final int RESPONSE_INVALID_OBJECT_PROP_CODE = 43009;
    public static final int RESPONSE_INVALID_OBJECT_PROP_FORMAT = 43010;
    public static final int RESPONSE_INVALID_OBJECT_PROP_VALUE = 43011;
    public static final int RESPONSE_INVALID_OBJECT_REFERENCE = 43012;
    public static final int RESPONSE_INVALID_PARAMETER = 8221;
    public static final int RESPONSE_INVALID_PARENT_OBJECT = 8218;
    public static final int RESPONSE_INVALID_STORAGE_ID = 8200;
    public static final int RESPONSE_INVALID_TRANSACTION_ID = 8196;
    public static final int RESPONSE_NO_THUMBNAIL_PRESENT = 8208;
    public static final int RESPONSE_NO_VALID_OBJECT_INFO = 8213;
    public static final int RESPONSE_OBJECT_PROP_NOT_SUPPORTED = 43018;
    public static final int RESPONSE_OBJECT_TOO_LARGE = 43017;
    public static final int RESPONSE_OBJECT_WRITE_PROTECTED = 8205;
    public static final int RESPONSE_OK = 8193;
    public static final int RESPONSE_OPERATION_NOT_SUPPORTED = 8197;
    public static final int RESPONSE_PARAMETER_NOT_SUPPORTED = 8198;
    public static final int RESPONSE_PARTIAL_DELETION = 8210;
    public static final int RESPONSE_SELF_TEST_FAILED = 8209;
    public static final int RESPONSE_SESSION_ALREADY_OPEN = 8222;
    public static final int RESPONSE_SESSION_NOT_OPEN = 8195;
    public static final int RESPONSE_SPECIFICATION_BY_DEPTH_UNSUPPORTED = 43016;
    public static final int RESPONSE_SPECIFICATION_BY_FORMAT_UNSUPPORTED = 8212;
    public static final int RESPONSE_SPECIFICATION_BY_GROUP_UNSUPPORTED = 43015;
    public static final int RESPONSE_SPECIFICATION_OF_DESTINATION_UNSUPPORTED = 8224;
    public static final int RESPONSE_STORAGE_FULL = 8204;
    public static final int RESPONSE_STORE_NOT_AVAILABLE = 8211;
    public static final int RESPONSE_STORE_READ_ONLY = 8206;
    public static final int RESPONSE_TRANSACTION_CANCELLED = 8223;
    public static final int RESPONSE_UNDEFINED = 8192;
    public static final int RESPONSE_UNKNOWN_VENDOR_CODE = 8215;
    public static final int TYPE_AINT128 = 16393;
    public static final int TYPE_AINT16 = 16387;
    public static final int TYPE_AINT32 = 16389;
    public static final int TYPE_AINT64 = 16391;
    public static final int TYPE_AINT8 = 16385;
    public static final int TYPE_AUINT128 = 16394;
    public static final int TYPE_AUINT16 = 16388;
    public static final int TYPE_AUINT32 = 16390;
    public static final int TYPE_AUINT64 = 16392;
    public static final int TYPE_AUINT8 = 16386;
    public static final int TYPE_INT128 = 9;
    public static final int TYPE_INT16 = 3;
    public static final int TYPE_INT32 = 5;
    public static final int TYPE_INT64 = 7;
    public static final int TYPE_INT8 = 1;
    public static final int TYPE_STR = 65535;
    public static final int TYPE_UINT128 = 10;
    public static final int TYPE_UINT16 = 4;
    public static final int TYPE_UINT32 = 6;
    public static final int TYPE_UINT64 = 8;
    public static final int TYPE_UINT8 = 2;
    public static final int TYPE_UNDEFINED = 0;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.mtp.MtpConstants.<init>():void, dex: 
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
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 9 more
        */
    public MtpConstants() {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.mtp.MtpConstants.<init>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpConstants.<init>():void");
    }

    public static boolean isAbstractObject(int format) {
        switch (format) {
            case FORMAT_ABSTRACT_MULTIMEDIA_ALBUM /*47617*/:
            case FORMAT_ABSTRACT_IMAGE_ALBUM /*47618*/:
            case FORMAT_ABSTRACT_AUDIO_ALBUM /*47619*/:
            case FORMAT_ABSTRACT_VIDEO_ALBUM /*47620*/:
            case FORMAT_ABSTRACT_AV_PLAYLIST /*47621*/:
            case FORMAT_ABSTRACT_AUDIO_PLAYLIST /*47625*/:
            case FORMAT_ABSTRACT_VIDEO_PLAYLIST /*47626*/:
            case FORMAT_ABSTRACT_MEDIACAST /*47627*/:
            case FORMAT_ABSTRACT_DOCUMENT /*47745*/:
                return true;
            default:
                return false;
        }
    }
}
