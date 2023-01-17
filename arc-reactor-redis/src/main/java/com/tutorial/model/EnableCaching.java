package com.tutorial.model;

import java.io.Serializable;

public interface EnableCaching extends Serializable  {
	String uniqueId();
	String collectionName();
	Object getPayload();
}
