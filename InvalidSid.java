package com.ty.assaignment;

public class InvalidSid extends Throwable {
public String getMessage() {
	return "Student Id range should be 1-5";
}
}
