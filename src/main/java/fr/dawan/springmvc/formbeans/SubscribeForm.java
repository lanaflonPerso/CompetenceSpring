package fr.dawan.springmvc.formbeans;

public class SubscribeForm {

	private int selectedTrainingId;
	
	
	private int selectedSessionId;

	private String participantName;
	
	private String participantEmail;
	
	public int getSelectedTrainingId() {
		return selectedTrainingId;
	}

	public void setSelectedTrainingId(int selectedTrainingId) {
		this.selectedTrainingId = selectedTrainingId;
	}

	
	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getParticipantEmail() {
		return participantEmail;
	}

	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}

	public int getSelectedSessionId() {
		return selectedSessionId;
	}

	public void setSelectedSessionId(int selectedSessionId) {
		this.selectedSessionId = selectedSessionId;
	}
	
	
}
