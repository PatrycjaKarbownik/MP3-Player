package application;

public class NotDirectoryException extends Exception {

    public NotDirectoryException() {
    }

    public NotDirectoryException(String arg0) {
        super(arg0);
    }

    public NotDirectoryException(Throwable arg0) {
        super(arg0);
    }

    public NotDirectoryException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public NotDirectoryException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
