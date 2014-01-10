package REST;

import java.nio.channels.SocketChannel;

public interface IDataHandler {
	void handleData(String request, SocketChannel channel) throws Exception;
}
