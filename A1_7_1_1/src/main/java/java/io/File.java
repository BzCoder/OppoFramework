package java.io;

import dalvik.system.VMRuntime;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
public class File implements Serializable, Comparable<File> {
    /* renamed from: -assertionsDisabled */
    static final /* synthetic */ boolean f10-assertionsDisabled = false;
    private static final FileSystem fs = null;
    public static final String pathSeparator = null;
    public static final char pathSeparatorChar = '\u0000';
    public static final String separator = null;
    public static final char separatorChar = '\u0000';
    private static final long serialVersionUID = 301077366599181567L;
    private String path;
    private transient int prefixLength;
    private transient PathStatus status;

    /*  JADX ERROR: NullPointerException in pass: EnumVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    private enum PathStatus {
        ;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: java.io.File.PathStatus.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 10 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: java.io.File.PathStatus.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: java.io.File.PathStatus.<clinit>():void");
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e9 in method: java.io.File.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 00e9 in method: java.io.File.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.File.<clinit>():void");
    }

    final boolean isInvalid() {
        if (this.status == null) {
            PathStatus pathStatus;
            if (this.path.indexOf(0) < 0) {
                pathStatus = PathStatus.CHECKED;
            } else {
                pathStatus = PathStatus.INVALID;
            }
            this.status = pathStatus;
        }
        return this.status == PathStatus.INVALID ? true : f10-assertionsDisabled;
    }

    int getPrefixLength() {
        return this.prefixLength;
    }

    private File(String pathname, int prefixLength) {
        this.status = null;
        this.path = pathname;
        this.prefixLength = prefixLength;
    }

    private File(String child, File parent) {
        Object obj = null;
        this.status = null;
        if (!f10-assertionsDisabled) {
            if ((parent.path != null ? 1 : null) == null) {
                throw new AssertionError();
            }
        }
        if (!f10-assertionsDisabled) {
            if (!parent.path.equals("")) {
                int obj2 = 1;
            }
            if (obj2 == null) {
                throw new AssertionError();
            }
        }
        this.path = fs.resolve(parent.path, child);
        this.prefixLength = parent.prefixLength;
    }

    public File(String pathname) {
        this.status = null;
        if (pathname == null) {
            throw new NullPointerException();
        }
        this.path = fs.normalize(pathname);
        this.prefixLength = fs.prefixLength(this.path);
    }

    public File(String parent, String child) {
        this.status = null;
        if (child == null) {
            throw new NullPointerException();
        }
        if (parent == null || parent.isEmpty()) {
            this.path = fs.normalize(child);
        } else {
            this.path = fs.resolve(fs.normalize(parent), fs.normalize(child));
        }
        this.prefixLength = fs.prefixLength(this.path);
    }

    public File(File parent, String child) {
        this.status = null;
        if (child == null) {
            throw new NullPointerException();
        }
        if (parent == null) {
            this.path = fs.normalize(child);
        } else if (parent.path.equals("")) {
            this.path = fs.resolve(fs.getDefaultParent(), fs.normalize(child));
        } else {
            this.path = fs.resolve(parent.path, fs.normalize(child));
        }
        this.prefixLength = fs.prefixLength(this.path);
    }

    public File(URI uri) {
        this.status = null;
        if (!uri.isAbsolute()) {
            throw new IllegalArgumentException("URI is not absolute");
        } else if (uri.isOpaque()) {
            throw new IllegalArgumentException("URI is not hierarchical");
        } else {
            String scheme = uri.getScheme();
            if (scheme == null || !scheme.equalsIgnoreCase("file")) {
                throw new IllegalArgumentException("URI scheme is not \"file\"");
            } else if (uri.getAuthority() != null) {
                throw new IllegalArgumentException("URI has an authority component");
            } else if (uri.getFragment() != null) {
                throw new IllegalArgumentException("URI has a fragment component");
            } else if (uri.getQuery() != null) {
                throw new IllegalArgumentException("URI has a query component");
            } else {
                String p = uri.getPath();
                if (p.equals("")) {
                    throw new IllegalArgumentException("URI path component is empty");
                }
                p = fs.fromURIPath(p);
                if (separatorChar != '/') {
                    p = p.replace('/', separatorChar);
                }
                this.path = fs.normalize(p);
                this.prefixLength = fs.prefixLength(this.path);
            }
        }
    }

    public String getName() {
        int index = this.path.lastIndexOf(separatorChar);
        if (index < this.prefixLength) {
            return this.path.substring(this.prefixLength);
        }
        return this.path.substring(index + 1);
    }

    public String getParent() {
        int index = this.path.lastIndexOf(separatorChar);
        if (index >= this.prefixLength) {
            return this.path.substring(0, index);
        }
        if (this.prefixLength <= 0 || this.path.length() <= this.prefixLength) {
            return null;
        }
        return this.path.substring(0, this.prefixLength);
    }

    public File getParentFile() {
        String p = getParent();
        if (p == null) {
            return null;
        }
        return new File(p, this.prefixLength);
    }

    public String getPath() {
        return this.path;
    }

    public boolean isAbsolute() {
        return fs.isAbsolute(this);
    }

    public String getAbsolutePath() {
        return fs.resolve(this);
    }

    public File getAbsoluteFile() {
        String absPath = getAbsolutePath();
        return new File(absPath, fs.prefixLength(absPath));
    }

    public String getCanonicalPath() throws IOException {
        if (!isInvalid()) {
            return fs.canonicalize(fs.resolve(this));
        }
        throw new IOException("Invalid file path");
    }

    public File getCanonicalFile() throws IOException {
        String canonPath = getCanonicalPath();
        return new File(canonPath, fs.prefixLength(canonPath));
    }

    private static String slashify(String path, boolean isDirectory) {
        String p = path;
        if (separatorChar != '/') {
            p = path.replace(separatorChar, '/');
        }
        if (!p.startsWith("/")) {
            p = "/" + p;
        }
        if (p.endsWith("/") || !isDirectory) {
            return p;
        }
        return p + "/";
    }

    @Deprecated
    public URL toURL() throws MalformedURLException {
        if (!isInvalid()) {
            return new URL("file", "", slashify(getAbsolutePath(), getAbsoluteFile().isDirectory()));
        }
        throw new MalformedURLException("Invalid file path");
    }

    public URI toURI() {
        try {
            File f = getAbsoluteFile();
            String sp = slashify(f.getPath(), f.isDirectory());
            if (sp.startsWith("//")) {
                sp = "//" + sp;
            }
            return new URI("file", null, sp, null);
        } catch (Throwable x) {
            throw new Error(x);
        }
    }

    public boolean canRead() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.checkAccess(this, 4);
    }

    public boolean canWrite() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.checkAccess(this, 2);
    }

    public boolean exists() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.checkAccess(this, 8);
    }

    public boolean isDirectory() {
        boolean z = f10-assertionsDisabled;
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        if ((fs.getBooleanAttributes(this) & 4) != 0) {
            z = true;
        }
        return z;
    }

    public boolean isFile() {
        boolean z = f10-assertionsDisabled;
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        if ((fs.getBooleanAttributes(this) & 2) != 0) {
            z = true;
        }
        return z;
    }

    public boolean isHidden() {
        boolean z = f10-assertionsDisabled;
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        if ((fs.getBooleanAttributes(this) & 8) != 0) {
            z = true;
        }
        return z;
    }

    public long lastModified() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(this.path);
        }
        if (isInvalid()) {
            return 0;
        }
        return fs.getLastModifiedTime(this);
    }

    public long length() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(this.path);
        }
        if (isInvalid()) {
            return 0;
        }
        return fs.getLength(this);
    }

    public boolean createNewFile() throws IOException {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
        }
        if (!isInvalid()) {
            return fs.createFileExclusively(this.path);
        }
        throw new IOException("Invalid file path");
    }

    public boolean delete() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkDelete(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.delete(this);
    }

    public void deleteOnExit() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkDelete(this.path);
        }
        if (!isInvalid()) {
            DeleteOnExitHook.add(this.path);
        }
    }

    public String[] list() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(this.path);
        }
        if (isInvalid()) {
            return null;
        }
        return fs.list(this);
    }

    public String[] list(FilenameFilter filter) {
        String[] names = list();
        if (names == null || filter == null) {
            return names;
        }
        List<String> v = new ArrayList();
        for (int i = 0; i < names.length; i++) {
            if (filter.accept(this, names[i])) {
                v.add(names[i]);
            }
        }
        return (String[]) v.toArray(new String[v.size()]);
    }

    public File[] listFiles() {
        String[] ss = list();
        if (ss == null) {
            if (!separator.equals(this.path) || VMRuntime.getRuntime().getTargetSdkVersion() > 23) {
                return null;
            }
            System.logE("Can not read rootfs anymore, return a fake result!", new Throwable());
            ss = new String[1];
            ss[0] = separator + "sdcard";
        }
        int n = ss.length;
        File[] fs = new File[n];
        for (int i = 0; i < n; i++) {
            fs[i] = new File(ss[i], this);
        }
        return fs;
    }

    public File[] listFiles(FilenameFilter filter) {
        String[] ss = list();
        if (ss == null) {
            return null;
        }
        ArrayList<File> files = new ArrayList();
        for (String s : ss) {
            if (filter == null || filter.accept(this, s)) {
                files.add(new File(s, this));
            }
        }
        return (File[]) files.toArray(new File[files.size()]);
    }

    public File[] listFiles(FileFilter filter) {
        String[] ss = list();
        if (ss == null) {
            return null;
        }
        ArrayList<File> files = new ArrayList();
        for (String s : ss) {
            File f = new File(s, this);
            if (filter == null || filter.accept(f)) {
                files.add(f);
            }
        }
        return (File[]) files.toArray(new File[files.size()]);
    }

    public boolean mkdir() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.createDirectory(this);
    }

    public boolean mkdirs() {
        boolean z = f10-assertionsDisabled;
        if (exists()) {
            return f10-assertionsDisabled;
        }
        if (mkdir()) {
            return true;
        }
        try {
            File canonFile = getCanonicalFile();
            File parent = canonFile.getParentFile();
            if (parent != null && (parent.mkdirs() || parent.exists())) {
                z = canonFile.mkdir();
            }
            return z;
        } catch (IOException e) {
            return f10-assertionsDisabled;
        }
    }

    public boolean renameTo(File dest) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
            security.checkWrite(dest.path);
        }
        if (dest == null) {
            throw new NullPointerException();
        } else if (isInvalid() || dest.isInvalid()) {
            return f10-assertionsDisabled;
        } else {
            return fs.rename(this, dest);
        }
    }

    public boolean setLastModified(long time) {
        if (time < 0) {
            throw new IllegalArgumentException("Negative time");
        }
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.setLastModifiedTime(this, time);
    }

    public boolean setReadOnly() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.setReadOnly(this);
    }

    public boolean setWritable(boolean writable, boolean ownerOnly) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.setPermission(this, 2, writable, ownerOnly);
    }

    public boolean setWritable(boolean writable) {
        return setWritable(writable, true);
    }

    public boolean setReadable(boolean readable, boolean ownerOnly) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.setPermission(this, 4, readable, ownerOnly);
    }

    public boolean setReadable(boolean readable) {
        return setReadable(readable, true);
    }

    public boolean setExecutable(boolean executable, boolean ownerOnly) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.setPermission(this, 1, executable, ownerOnly);
    }

    public boolean setExecutable(boolean executable) {
        return setExecutable(executable, true);
    }

    public boolean canExecute() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkExec(this.path);
        }
        if (isInvalid()) {
            return f10-assertionsDisabled;
        }
        return fs.checkAccess(this, 1);
    }

    public static File[] listRoots() {
        return fs.listRoots();
    }

    public long getTotalSpace() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
            sm.checkRead(this.path);
        }
        if (isInvalid()) {
            return 0;
        }
        return fs.getSpace(this, 0);
    }

    public long getFreeSpace() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
            sm.checkRead(this.path);
        }
        if (isInvalid()) {
            return 0;
        }
        return fs.getSpace(this, 1);
    }

    public long getUsableSpace() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
            sm.checkRead(this.path);
        }
        if (isInvalid()) {
            return 0;
        }
        return fs.getSpace(this, 2);
    }

    private static File generateTempFile(String prefix, String suffix, File dir) throws IOException {
        int n = Math.randomIntInternal();
        if (n == Integer.MIN_VALUE) {
            n = 0;
        } else {
            n = Math.abs(n);
        }
        String name = prefix + Integer.toString(n) + suffix;
        File f = new File(dir, name);
        if (name.equals(f.getName())) {
            return f;
        }
        throw new IOException("Unable to create temporary file");
    }

    public static File createTempFile(String prefix, String suffix, File directory) throws IOException {
        if (prefix.length() < 3) {
            throw new IllegalArgumentException("Prefix string too short");
        }
        File tmpdir;
        File f;
        if (suffix == null) {
            suffix = ".tmp";
        }
        if (directory != null) {
            tmpdir = directory;
        } else {
            tmpdir = new File(System.getProperty("java.io.tmpdir", "."));
        }
        do {
            try {
                f = generateTempFile(prefix, suffix, tmpdir);
            } catch (SecurityException se) {
                if (directory == null) {
                    throw new SecurityException("Unable to create temporary file");
                }
                throw se;
            }
        } while (f.exists());
        if (f.createNewFile()) {
            return f;
        }
        throw new IOException("Unable to create temporary file");
    }

    public static File createTempFile(String prefix, String suffix) throws IOException {
        return createTempFile(prefix, suffix, null);
    }

    public int compareTo(File pathname) {
        return fs.compare(this, pathname);
    }

    public boolean equals(Object obj) {
        boolean z = f10-assertionsDisabled;
        if (obj == null || !(obj instanceof File)) {
            return f10-assertionsDisabled;
        }
        if (compareTo((File) obj) == 0) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return fs.hashCode(this);
    }

    public String toString() {
        return getPath();
    }

    private synchronized void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeChar(separatorChar);
    }

    private synchronized void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String pathField = (String) s.readFields().get("path", null);
        char sep = s.readChar();
        if (sep != separatorChar) {
            pathField = pathField.replace(sep, separatorChar);
        }
        this.path = fs.normalize(pathField);
        this.prefixLength = fs.prefixLength(this.path);
    }
}
