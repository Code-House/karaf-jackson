package org.code_house.jackson.itest;

import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*;

import static org.junit.Assert.*;

import static org.ops4j.pax.exam.CoreOptions.*;

import java.util.EnumSet;

import javax.inject.Inject;

import org.apache.karaf.features.FeaturesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class FeaturesIntegrationTest {

    private final static EnumSet<FeaturesService.Option> INSTALL_OPTIONS = EnumSet.of(FeaturesService.Option.Verbose);

    @Inject
    private FeaturesService features;

    @Configuration
    public Option[] config() {
        String featuresUrl = maven("org.code-house.jackson", "features").classifier("features").type("xml").versionAsInProject().getURL();
        String karafVersion = MavenUtils.getArtifactVersion("org.apache.karaf", "apache-karaf");
        MavenArtifactUrlReference frameworkURL = maven("org.apache.karaf", "apache-karaf").type("zip").version(karafVersion);

        return new Option[]{
            karafDistributionConfiguration().karafVersion(karafVersion).frameworkUrl(frameworkURL),
            editConfigurationFileExtend("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + featuresUrl)
        };
    }

    @Test
    public void testInstallJacksonAnnotations() throws Exception {
        features.installFeature("jackson-annotations", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-core");
    }

    @Test
    public void testInstallJacksonCore() throws Exception {
        features.installFeature("jackson-core", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-core");
    }

    @Test
    public void testInstallJacksonDatabind() throws Exception {
        features.installFeature("jackson-databind", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-databind");
    }

    @Test
    public void testInstallJacksonDataformatYaml() throws Exception {
        features.installFeature("jackson-dataformat-yaml", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-dataformat-yaml");
    }

    @Test
    public void testInstallJacksonJaxrsJson() throws Exception {
        features.installFeature("jackson-jaxrs-json-provider", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-jaxrs-json-provider");
    }

    private void assertFeatureInstalled(String name) throws Exception {
        assertTrue("Feature " + name + " should be installed", features.isInstalled(features.getFeature(name)));
    }

}
