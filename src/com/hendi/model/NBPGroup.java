package com.hendi.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NBPGroup {

    @XmlElement(name = "NBP")
    private List<NBP> nbps = new ArrayList<>();

	public List<NBP> getNBP() {
		return nbps;
	}

	public void setNBP(List<NBP> nbps) {
		this.nbps = nbps;
	}

}
