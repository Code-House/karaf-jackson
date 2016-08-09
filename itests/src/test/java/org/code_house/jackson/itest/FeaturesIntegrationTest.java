/*
 * (C) Copyright 2016 Code-House and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import org.ops4j.pax.exam.spi.reactors.PerMethod;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
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
    public void testInstallJacksonDataformatAvro() throws Exception {
        features.installFeature("jackson-dataformat-avro", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-dataformat-avro");
    }

    @Test
    public void testInstallJacksonDataformatCsv() throws Exception {
        features.installFeature("jackson-dataformat-csv", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-dataformat-csv");
    }

    @Test
    public void testInstallJacksonDataformatCbor() throws Exception {
        features.installFeature("jackson-dataformat-cbor", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-dataformat-cbor");
    }

    @Test
    public void testInstallJacksonDataformatYaml() throws Exception {
        features.installFeature("jackson-dataformat-yaml", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-dataformat-yaml");
    }

    @Test
    public void testInstallJacksonDataformatProtobuf() throws Exception {
        features.installFeature("jackson-dataformat-protobuf", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-dataformat-protobuf");
    }

    @Test
    public void testInstallJacksonDataformatSmile() throws Exception {
        features.installFeature("jackson-dataformat-smile", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-dataformat-smile");
    }

    @Test
    public void testInstallJacksonDataformatXml() throws Exception {
        features.installFeature("jackson-dataformat-xml", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-dataformat-xml");
    }

    // datatype

    @Test
    public void testInstallJacksonDatatypeJoda() throws Exception {
        features.installFeature("jackson-datatype-joda", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-datatype-joda");
    }

    // jaxrs

    @Test
    public void testInstallJacksonJaxrsCbor() throws Exception {
        features.installFeature("jackson-jaxrs-cbor-provider", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-jaxrs-cbor-provider");
    }

    @Test
    public void testInstallJacksonJaxrsJson() throws Exception {
        features.installFeature("jackson-jaxrs-json-provider", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-jaxrs-json-provider");
    }

    @Test
    public void testInstallJacksonJaxrsSmile() throws Exception {
        features.installFeature("jackson-jaxrs-smile-provider", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-jaxrs-smile-provider");
    }

    @Test
    public void testInstallJacksonJaxrsXml() throws Exception {
        features.installFeature("jackson-jaxrs-xml-provider", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-jaxrs-xml-provider");
    }

    @Test
    public void testInstallJacksonJaxrsYaml() throws Exception {
        features.installFeature("jackson-jaxrs-yaml-provider", INSTALL_OPTIONS);

        assertFeatureInstalled("jackson-jaxrs-yaml-provider");
    }

    private void assertFeatureInstalled(String name) throws Exception {
        assertTrue("Feature " + name + " should be installed", features.isInstalled(features.getFeature(name)));
    }

}
