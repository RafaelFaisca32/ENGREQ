package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Hive} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HiveDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private ApiaryDTO apiary;

    private int apiaryId;

    public void setApiaryId(int apiaryId) {
        this.apiaryId = apiaryId;
    }

    public Long getApiaryId() {
        return (long) apiaryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ApiaryDTO getApiary() {
        return apiary;
    }

    public void setApiary(ApiaryDTO apiary) {
        this.apiary = apiary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HiveDTO)) {
            return false;
        }

        HiveDTO hiveDTO = (HiveDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hiveDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HiveDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", apiary=" + getApiary() +
            "}";
    }
}
