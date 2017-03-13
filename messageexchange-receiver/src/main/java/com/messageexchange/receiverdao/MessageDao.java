package com.messageexchange.receiverdao;

import io.reactivex.internal.operators.observable.ObservableFromArray;

public interface MessageDao {
	void saveMessage(String message);
	String getLastMessage();
	ObservableFromArray<String> getAllMessages();
}
