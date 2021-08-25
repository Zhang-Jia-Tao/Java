package com.sdkj.Color;

import org.springframework.core.type.AnnotationMetadata;

public class ImportSelector implements org.springframework.context.annotation.ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String[] strings={"com.sdkj.Color.blue"};
        return strings;
    }
}
