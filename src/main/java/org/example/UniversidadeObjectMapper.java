package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UniversidadeObjectMapper {
    private String name;
    private List<String> webPages;
    private String alpha_two_code;
    private List<String> domains;
    private String stateProvince;
    private String country;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("web_pages")
    public List<String> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<String> webPages) {
        this.webPages = webPages;
    }

    @JsonProperty("alpha_two_code")
    public String getAlphaTwoCode() {
        return alpha_two_code;
    }

    public void setAlphaTwoCode(String alpha_two_code) {
        this.alpha_two_code = alpha_two_code;
    }

    @JsonProperty("domains")
    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    @JsonProperty("state-province")
    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "UniversidadeObjectMapper{" +
                "name='" + getName() + '\'' +
                ", webPages=" + getWebPages() +
                ", alphaTwoCode='" + getAlphaTwoCode() + '\'' +
                ", domains=" + domains +
                ", stateProvince='" + stateProvince + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
