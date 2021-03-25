package com.task.questanswer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue
    private int id;
    private String answer;
    private String posterName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", answer:'" + answer + '\'' +
                ", posterName:'" + posterName + '\'' +
                '}';
    }
}
