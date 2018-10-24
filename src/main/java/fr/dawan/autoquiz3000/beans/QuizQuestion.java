package fr.dawan.autoquiz3000.beans;

import java.io.Serializable;

import javax.persistence.ManyToOne;
import javax.persistence.Version;

public class QuizQuestion implements Serializable {
	private Long id;
	private String text;
	@ManyToOne
	private Quiz quiz;
	@Version int version;

}
