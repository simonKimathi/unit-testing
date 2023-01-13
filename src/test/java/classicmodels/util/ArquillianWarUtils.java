package classicmodels.util;

import java.io.File;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;


public class ArquillianWarUtils {

    private static final File[] ASSERTJ_ARTIFACT = Maven.resolver()
            .loadPomFromFile("pom.xml")
            .resolve("org.assertj:assertj-core")
            .withTransitivity().asFile();

    private static final File[] EXPRESSION_PARSER = Maven.resolver()
            .loadPomFromFile("pom.xml")
            .resolve("com.udojava:EvalEx")
            .withTransitivity().asFile();
 private static final File[] REST_ASSURED = Maven.resolver()
            .loadPomFromFile("pom.xml")
            .resolve("io.rest-assured:rest-assured")
            .withTransitivity().asFile();


    public static WebArchive getBasicWebArchive(String deploymentName) {
        return ShrinkWrap.create(WebArchive.class, deploymentName==null?"test.war":deploymentName)
                .addAsLibraries(ASSERTJ_ARTIFACT)
                .addAsLibraries(EXPRESSION_PARSER)
                .addAsLibraries(REST_ASSURED)
                .addAsWebInfResource("beans.xml")
                .addAsResource("META-INF/sql/insert.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }


    public static WebArchive importBuildOutput() {
        return ShrinkWrap.create(MavenImporter.class).loadPomFromFile("pom.xml")
                .importBuildOutput()
                .as(WebArchive.class);
    }
}
