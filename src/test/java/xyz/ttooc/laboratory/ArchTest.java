package xyz.ttooc.laboratory;

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
            .importPackages("xyz.ttooc.laboratory");

        noClasses()
            .that()
                .resideInAnyPackage("xyz.ttooc.laboratory.service..")
            .or()
                .resideInAnyPackage("xyz.ttooc.laboratory.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..xyz.ttooc.laboratory.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
