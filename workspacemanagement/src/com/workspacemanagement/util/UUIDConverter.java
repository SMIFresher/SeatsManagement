package com.workspacemanagement.util;

import java.util.UUID;

public class UUIDConverter {
	
	public static String uUIDToString(UUID id) {
		return id.toString();
	}
	
	public static UUID stringToUUID(String idString) {
		UUID uuid = UUID.fromString(idString);
		return uuid;
	}
}
