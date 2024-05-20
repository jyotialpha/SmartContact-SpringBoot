package com.project.SmartContactv1.ErrorHelper;

public class Message {
		private  String content;
		private  String type;
		/**
		 * @return the errorMessage
		 */
		public String getErrorMessage() {
			return content;
		}
		/**
		 * @param errorMessage the errorMessage to set
		 */
		public void setErrorMessage(String errorMessage) {
			this.content = errorMessage;
		}
		/**
		 * @return the errorType
		 */
		public String getErrorType() {
			return type;
		}
		/**
		 * @param errorType the errorType to set
		 */
		public void setErrorType(String errorType) {
			this.type = errorType;
		}
		public Message(String errorMessage, String errorType) {
			super();
			this.content = errorMessage;
			this.type = errorType;
		}
		@Override
		public String toString() {
			return "Message [errorMessage=" + content + ", errorType=" + type + "]";
		}
		public Message() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
