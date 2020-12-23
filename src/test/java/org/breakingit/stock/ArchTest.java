package org.breakingit.stock;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.breakingit.stock");

        noClasses()
            .that()
                .resideInAnyPackage("org.breakingit.stock.service..")
            .or()
                .resideInAnyPackage("org.breakingit.stock.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.breakingit.stock.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
