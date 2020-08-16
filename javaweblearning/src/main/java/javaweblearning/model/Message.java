package javaweblearning.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity
public class Message implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Message() {
		super();
	}
   
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private @NotNull String text;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
