package com.company.jmixpm.dataType;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter(autoApply = true)
public class ProjectLabelsConverter implements AttributeConverter<ProjectLabels, String> {

    @Override
    public String convertToDatabaseColumn(ProjectLabels attribute) {
        return Joiner.on(", ")
                .join(attribute.getLabels());
    }

    @Override
    public ProjectLabels convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            List<String> labels = Splitter.on(",")
                    .omitEmptyStrings()
                    .trimResults()
                    .splitToList(dbData);

            return new ProjectLabels(labels);
        }
    return null;
    }
}
