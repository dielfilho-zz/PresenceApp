package connection;

/**
 * Created by Daniel Filho on 9/23/16.
 */

public class Response {
    private boolean result;
    private Object data;

    public boolean getResult(){
        return result;
    }

    public Object getData(){
        return data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "result=" + result +
                ", data=" + data +
                '}';
    }
}
