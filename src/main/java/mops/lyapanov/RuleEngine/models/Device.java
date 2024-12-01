package mops.lyapanov.RuleEngine.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
    private String id;
    private String name;
    private DeviceType type;

    @JsonCreator
    public Device(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("type") DeviceType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }
}
