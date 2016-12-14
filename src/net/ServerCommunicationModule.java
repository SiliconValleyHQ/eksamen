package net;

class ServerCommunicationModule {

	private ServerConnection[] connections;

	private ServerConnection[] getConnections() {
		return connections;
	}

	ServerCommunicationModule(ServerConnection[] connections) {
		this.connections = connections;
	}

	/**
	 * Say to everyone.
	 *
	 * @param message
	 */
	void say(String message) {
		for (ServerConnection connection : getConnections()) {
			connection.say(message);
		}
	}

	/**
	 * Say to specific communication module.
	 *
	 * @param connection
	 * @param message
	 */
	void say(ServerConnection connection, String message) {
		connection.say(message);
	}

	String readLine(ServerConnection connection) {
		return connection.readLine();
	}

}