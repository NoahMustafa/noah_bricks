package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ActivityLibraryAccessors laccForActivityLibraryAccessors = new ActivityLibraryAccessors(owner);
    private final AnimeLibraryAccessors laccForAnimeLibraryAccessors = new AnimeLibraryAccessors(owner);
    private final CoilLibraryAccessors laccForCoilLibraryAccessors = new CoilLibraryAccessors(owner);
    private final ConscryptLibraryAccessors laccForConscryptLibraryAccessors = new ConscryptLibraryAccessors(owner);
    private final CoreLibraryAccessors laccForCoreLibraryAccessors = new CoreLibraryAccessors(owner);
    private final CryptographyLibraryAccessors laccForCryptographyLibraryAccessors = new CryptographyLibraryAccessors(owner);
    private final DesugarLibraryAccessors laccForDesugarLibraryAccessors = new DesugarLibraryAccessors(owner);
    private final EspressoLibraryAccessors laccForEspressoLibraryAccessors = new EspressoLibraryAccessors(owner);
    private final ExtLibraryAccessors laccForExtLibraryAccessors = new ExtLibraryAccessors(owner);
    private final FragmentLibraryAccessors laccForFragmentLibraryAccessors = new FragmentLibraryAccessors(owner);
    private final InstancioLibraryAccessors laccForInstancioLibraryAccessors = new InstancioLibraryAccessors(owner);
    private final JacksonLibraryAccessors laccForJacksonLibraryAccessors = new JacksonLibraryAccessors(owner);
    private final JunitLibraryAccessors laccForJunitLibraryAccessors = new JunitLibraryAccessors(owner);
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final KotlinxLibraryAccessors laccForKotlinxLibraryAccessors = new KotlinxLibraryAccessors(owner);
    private final KtorLibraryAccessors laccForKtorLibraryAccessors = new KtorLibraryAccessors(owner);
    private final LifecycleLibraryAccessors laccForLifecycleLibraryAccessors = new LifecycleLibraryAccessors(owner);
    private final Media3LibraryAccessors laccForMedia3LibraryAccessors = new Media3LibraryAccessors(owner);
    private final NavigationLibraryAccessors laccForNavigationLibraryAccessors = new NavigationLibraryAccessors(owner);
    private final NextlibLibraryAccessors laccForNextlibLibraryAccessors = new NextlibLibraryAccessors(owner);
    private final PaletteLibraryAccessors laccForPaletteLibraryAccessors = new PaletteLibraryAccessors(owner);
    private final PreferenceLibraryAccessors laccForPreferenceLibraryAccessors = new PreferenceLibraryAccessors(owner);
    private final PreviewseekbarLibraryAccessors laccForPreviewseekbarLibraryAccessors = new PreviewseekbarLibraryAccessors(owner);
    private final QrcodeLibraryAccessors laccForQrcodeLibraryAccessors = new QrcodeLibraryAccessors(owner);
    private final WorkLibraryAccessors laccForWorkLibraryAccessors = new WorkLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for annotation (androidx.annotation:annotation)
     * with versionRef 'annotation'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAnnotation() {
            return create("annotation");
    }

        /**
         * Creates a dependency provider for appcompat (androidx.appcompat:appcompat)
     * with versionRef 'appcompat'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAppcompat() {
            return create("appcompat");
    }

        /**
         * Creates a dependency provider for biometric (androidx.biometric:biometric)
     * with versionRef 'biometric'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBiometric() {
            return create("biometric");
    }

        /**
         * Creates a dependency provider for colorpicker (com.github.recloudstream:color-picker-android)
     * with versionRef 'colorpicker'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getColorpicker() {
            return create("colorpicker");
    }

        /**
         * Creates a dependency provider for constraintlayout (androidx.constraintlayout:constraintlayout)
     * with versionRef 'constraintlayout'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getConstraintlayout() {
            return create("constraintlayout");
    }

        /**
         * Creates a dependency provider for databinding (androidx.databinding:viewbinding)
     * with versionRef 'androidGradlePlugin'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDatabinding() {
            return create("databinding");
    }

        /**
         * Creates a dependency provider for json (org.json:json)
     * with versionRef 'json'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJson() {
            return create("json");
    }

        /**
         * Creates a dependency provider for jsoup (org.jsoup:jsoup)
     * with versionRef 'jsoup'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJsoup() {
            return create("jsoup");
    }

        /**
         * Creates a dependency provider for juniversalchardet (com.github.albfernandez:juniversalchardet)
     * with versionRef 'juniversalchardet'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJuniversalchardet() {
            return create("juniversalchardet");
    }

        /**
         * Creates a dependency provider for ksoup (com.fleeksoft.ksoup:ksoup)
     * with versionRef 'ksoup'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKsoup() {
            return create("ksoup");
    }

        /**
         * Creates a dependency provider for material (com.google.android.material:material)
     * with versionRef 'material'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMaterial() {
            return create("material");
    }

        /**
         * Creates a dependency provider for newpipeextractor (com.github.teamnewpipe:NewPipeExtractor)
     * with versionRef 'newpipeextractor'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNewpipeextractor() {
            return create("newpipeextractor");
    }

        /**
         * Creates a dependency provider for nicehttp (com.github.Blatzar:NiceHttp)
     * with versionRef 'nicehttp'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNicehttp() {
            return create("nicehttp");
    }

        /**
         * Creates a dependency provider for overlappingpanels (com.github.discord:OverlappingPanels)
     * with versionRef 'overlappingpanels'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getOverlappingpanels() {
            return create("overlappingpanels");
    }

        /**
         * Creates a dependency provider for rhino (org.mozilla:rhino)
     * with versionRef 'rhino'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRhino() {
            return create("rhino");
    }

        /**
         * Creates a dependency provider for safefile (com.github.LagradOst:SafeFile)
     * with versionRef 'safefile'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSafefile() {
            return create("safefile");
    }

        /**
         * Creates a dependency provider for shimmer (com.facebook.shimmer:shimmer)
     * with versionRef 'shimmer'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getShimmer() {
            return create("shimmer");
    }

        /**
         * Creates a dependency provider for torrentserver (com.github.recloudstream:torrentserver)
     * with versionRef 'torrentserver'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTorrentserver() {
            return create("torrentserver");
    }

        /**
         * Creates a dependency provider for tvprovider (androidx.tvprovider:tvprovider)
     * with versionRef 'tvprovider'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTvprovider() {
            return create("tvprovider");
    }

        /**
         * Creates a dependency provider for video (com.google.android.mediahome:video)
     * with versionRef 'video'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getVideo() {
            return create("video");
    }

        /**
         * Creates a dependency provider for zipline (app.cash.zipline:zipline-android)
     * with versionRef 'zipline'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getZipline() {
            return create("zipline");
    }

    /**
     * Returns the group of libraries at activity
     */
    public ActivityLibraryAccessors getActivity() {
        return laccForActivityLibraryAccessors;
    }

    /**
     * Returns the group of libraries at anime
     */
    public AnimeLibraryAccessors getAnime() {
        return laccForAnimeLibraryAccessors;
    }

    /**
     * Returns the group of libraries at coil
     */
    public CoilLibraryAccessors getCoil() {
        return laccForCoilLibraryAccessors;
    }

    /**
     * Returns the group of libraries at conscrypt
     */
    public ConscryptLibraryAccessors getConscrypt() {
        return laccForConscryptLibraryAccessors;
    }

    /**
     * Returns the group of libraries at core
     */
    public CoreLibraryAccessors getCore() {
        return laccForCoreLibraryAccessors;
    }

    /**
     * Returns the group of libraries at cryptography
     */
    public CryptographyLibraryAccessors getCryptography() {
        return laccForCryptographyLibraryAccessors;
    }

    /**
     * Returns the group of libraries at desugar
     */
    public DesugarLibraryAccessors getDesugar() {
        return laccForDesugarLibraryAccessors;
    }

    /**
     * Returns the group of libraries at espresso
     */
    public EspressoLibraryAccessors getEspresso() {
        return laccForEspressoLibraryAccessors;
    }

    /**
     * Returns the group of libraries at ext
     */
    public ExtLibraryAccessors getExt() {
        return laccForExtLibraryAccessors;
    }

    /**
     * Returns the group of libraries at fragment
     */
    public FragmentLibraryAccessors getFragment() {
        return laccForFragmentLibraryAccessors;
    }

    /**
     * Returns the group of libraries at instancio
     */
    public InstancioLibraryAccessors getInstancio() {
        return laccForInstancioLibraryAccessors;
    }

    /**
     * Returns the group of libraries at jackson
     */
    public JacksonLibraryAccessors getJackson() {
        return laccForJacksonLibraryAccessors;
    }

    /**
     * Returns the group of libraries at junit
     */
    public JunitLibraryAccessors getJunit() {
        return laccForJunitLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlin
     */
    public KotlinLibraryAccessors getKotlin() {
        return laccForKotlinLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlinx
     */
    public KotlinxLibraryAccessors getKotlinx() {
        return laccForKotlinxLibraryAccessors;
    }

    /**
     * Returns the group of libraries at ktor
     */
    public KtorLibraryAccessors getKtor() {
        return laccForKtorLibraryAccessors;
    }

    /**
     * Returns the group of libraries at lifecycle
     */
    public LifecycleLibraryAccessors getLifecycle() {
        return laccForLifecycleLibraryAccessors;
    }

    /**
     * Returns the group of libraries at media3
     */
    public Media3LibraryAccessors getMedia3() {
        return laccForMedia3LibraryAccessors;
    }

    /**
     * Returns the group of libraries at navigation
     */
    public NavigationLibraryAccessors getNavigation() {
        return laccForNavigationLibraryAccessors;
    }

    /**
     * Returns the group of libraries at nextlib
     */
    public NextlibLibraryAccessors getNextlib() {
        return laccForNextlibLibraryAccessors;
    }

    /**
     * Returns the group of libraries at palette
     */
    public PaletteLibraryAccessors getPalette() {
        return laccForPaletteLibraryAccessors;
    }

    /**
     * Returns the group of libraries at preference
     */
    public PreferenceLibraryAccessors getPreference() {
        return laccForPreferenceLibraryAccessors;
    }

    /**
     * Returns the group of libraries at previewseekbar
     */
    public PreviewseekbarLibraryAccessors getPreviewseekbar() {
        return laccForPreviewseekbarLibraryAccessors;
    }

    /**
     * Returns the group of libraries at qrcode
     */
    public QrcodeLibraryAccessors getQrcode() {
        return laccForQrcodeLibraryAccessors;
    }

    /**
     * Returns the group of libraries at work
     */
    public WorkLibraryAccessors getWork() {
        return laccForWorkLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class ActivityLibraryAccessors extends SubDependencyFactory {

        public ActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.activity:activity-ktx)
         * with versionRef 'activityKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("activity.ktx");
        }

    }

    public static class AnimeLibraryAccessors extends SubDependencyFactory {

        public AnimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for db (com.github.recloudstream:anime-db)
         * with versionRef 'animeDb'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDb() {
                return create("anime.db");
        }

    }

    public static class CoilLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final CoilNetworkLibraryAccessors laccForCoilNetworkLibraryAccessors = new CoilNetworkLibraryAccessors(owner);

        public CoilLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for coil (io.coil-kt.coil3:coil)
         * with versionRef 'coil'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("coil");
        }

        /**
         * Returns the group of libraries at coil.network
         */
        public CoilNetworkLibraryAccessors getNetwork() {
            return laccForCoilNetworkLibraryAccessors;
        }

    }

    public static class CoilNetworkLibraryAccessors extends SubDependencyFactory {

        public CoilNetworkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for okhttp (io.coil-kt.coil3:coil-network-okhttp)
         * with versionRef 'coil'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getOkhttp() {
                return create("coil.network.okhttp");
        }

    }

    public static class ConscryptLibraryAccessors extends SubDependencyFactory {

        public ConscryptLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (org.conscrypt:conscrypt-android)
         * with versionRef 'conscryptAndroid'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() {
                return create("conscrypt.android");
        }

    }

    public static class CoreLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public CoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.test:core)
         * with no version specified
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("core");
        }

            /**
             * Creates a dependency provider for ktx (androidx.core:core-ktx)
         * with versionRef 'coreKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("core.ktx");
        }

    }

    public static class CryptographyLibraryAccessors extends SubDependencyFactory {
        private final CryptographyProviderLibraryAccessors laccForCryptographyProviderLibraryAccessors = new CryptographyProviderLibraryAccessors(owner);

        public CryptographyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (dev.whyoleg.cryptography:cryptography-core)
         * with versionRef 'cryptography'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("cryptography.core");
        }

        /**
         * Returns the group of libraries at cryptography.provider
         */
        public CryptographyProviderLibraryAccessors getProvider() {
            return laccForCryptographyProviderLibraryAccessors;
        }

    }

    public static class CryptographyProviderLibraryAccessors extends SubDependencyFactory {

        public CryptographyProviderLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for optimal (dev.whyoleg.cryptography:cryptography-provider-optimal)
         * with versionRef 'cryptography'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getOptimal() {
                return create("cryptography.provider.optimal");
        }

    }

    public static class DesugarLibraryAccessors extends SubDependencyFactory {
        private final DesugarJdkLibraryAccessors laccForDesugarJdkLibraryAccessors = new DesugarJdkLibraryAccessors(owner);

        public DesugarLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at desugar.jdk
         */
        public DesugarJdkLibraryAccessors getJdk() {
            return laccForDesugarJdkLibraryAccessors;
        }

    }

    public static class DesugarJdkLibraryAccessors extends SubDependencyFactory {
        private final DesugarJdkLibsLibraryAccessors laccForDesugarJdkLibsLibraryAccessors = new DesugarJdkLibsLibraryAccessors(owner);

        public DesugarJdkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at desugar.jdk.libs
         */
        public DesugarJdkLibsLibraryAccessors getLibs() {
            return laccForDesugarJdkLibsLibraryAccessors;
        }

    }

    public static class DesugarJdkLibsLibraryAccessors extends SubDependencyFactory {

        public DesugarJdkLibsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for nio (com.android.tools:desugar_jdk_libs_nio)
         * with versionRef 'desugar.jdk.libs.nio'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getNio() {
                return create("desugar.jdk.libs.nio");
        }

    }

    public static class EspressoLibraryAccessors extends SubDependencyFactory {

        public EspressoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.test.espresso:espresso-core)
         * with versionRef 'espressoCore'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("espresso.core");
        }

    }

    public static class ExtLibraryAccessors extends SubDependencyFactory {

        public ExtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (androidx.test.ext:junit)
         * with versionRef 'junitVersion'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit() {
                return create("ext.junit");
        }

    }

    public static class FragmentLibraryAccessors extends SubDependencyFactory {

        public FragmentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.fragment:fragment-ktx)
         * with versionRef 'fragmentKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("fragment.ktx");
        }

    }

    public static class InstancioLibraryAccessors extends SubDependencyFactory {

        public InstancioLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.instancio:instancio-core)
         * with versionRef 'instancioCore'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("instancio.core");
        }

    }

    public static class JacksonLibraryAccessors extends SubDependencyFactory {
        private final JacksonModuleLibraryAccessors laccForJacksonModuleLibraryAccessors = new JacksonModuleLibraryAccessors(owner);

        public JacksonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at jackson.module
         */
        public JacksonModuleLibraryAccessors getModule() {
            return laccForJacksonModuleLibraryAccessors;
        }

    }

    public static class JacksonModuleLibraryAccessors extends SubDependencyFactory {

        public JacksonModuleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for kotlin (com.fasterxml.jackson.module:jackson-module-kotlin)
         * with versionRef 'jacksonModuleKotlin'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKotlin() {
                return create("jackson.module.kotlin");
        }

    }

    public static class JunitLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public JunitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (junit:junit)
         * with versionRef 'junit'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("junit");
        }

            /**
             * Creates a dependency provider for ktx (androidx.test.ext:junit-ktx)
         * with versionRef 'junitKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("junit.ktx");
        }

    }

    public static class KotlinLibraryAccessors extends SubDependencyFactory {

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for reflect (org.jetbrains.kotlin:kotlin-reflect)
         * with versionRef 'kotlinGradlePlugin'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getReflect() {
                return create("kotlin.reflect");
        }

            /**
             * Creates a dependency provider for test (org.jetbrains.kotlin:kotlin-test)
         * with versionRef 'kotlinGradlePlugin'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() {
                return create("kotlin.test");
        }

    }

    public static class KotlinxLibraryAccessors extends SubDependencyFactory {
        private final KotlinxCollectionsLibraryAccessors laccForKotlinxCollectionsLibraryAccessors = new KotlinxCollectionsLibraryAccessors(owner);
        private final KotlinxCoroutinesLibraryAccessors laccForKotlinxCoroutinesLibraryAccessors = new KotlinxCoroutinesLibraryAccessors(owner);
        private final KotlinxIoLibraryAccessors laccForKotlinxIoLibraryAccessors = new KotlinxIoLibraryAccessors(owner);
        private final KotlinxSerializationLibraryAccessors laccForKotlinxSerializationLibraryAccessors = new KotlinxSerializationLibraryAccessors(owner);

        public KotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for atomicfu (org.jetbrains.kotlinx:atomicfu)
         * with versionRef 'kotlinxAtomicfu'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAtomicfu() {
                return create("kotlinx.atomicfu");
        }

            /**
             * Creates a dependency provider for datetime (org.jetbrains.kotlinx:kotlinx-datetime)
         * with versionRef 'kotlinxDatetime'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDatetime() {
                return create("kotlinx.datetime");
        }

        /**
         * Returns the group of libraries at kotlinx.collections
         */
        public KotlinxCollectionsLibraryAccessors getCollections() {
            return laccForKotlinxCollectionsLibraryAccessors;
        }

        /**
         * Returns the group of libraries at kotlinx.coroutines
         */
        public KotlinxCoroutinesLibraryAccessors getCoroutines() {
            return laccForKotlinxCoroutinesLibraryAccessors;
        }

        /**
         * Returns the group of libraries at kotlinx.io
         */
        public KotlinxIoLibraryAccessors getIo() {
            return laccForKotlinxIoLibraryAccessors;
        }

        /**
         * Returns the group of libraries at kotlinx.serialization
         */
        public KotlinxSerializationLibraryAccessors getSerialization() {
            return laccForKotlinxSerializationLibraryAccessors;
        }

    }

    public static class KotlinxCollectionsLibraryAccessors extends SubDependencyFactory {

        public KotlinxCollectionsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for immutable (org.jetbrains.kotlinx:kotlinx-collections-immutable)
         * with versionRef 'kotlinxCollectionsImmutable'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getImmutable() {
                return create("kotlinx.collections.immutable");
        }

    }

    public static class KotlinxCoroutinesLibraryAccessors extends SubDependencyFactory {

        public KotlinxCoroutinesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.jetbrains.kotlinx:kotlinx-coroutines-core)
         * with versionRef 'kotlinxCoroutines'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("kotlinx.coroutines.core");
        }

            /**
             * Creates a dependency provider for test (org.jetbrains.kotlinx:kotlinx-coroutines-test)
         * with versionRef 'kotlinxCoroutines'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() {
                return create("kotlinx.coroutines.test");
        }

    }

    public static class KotlinxIoLibraryAccessors extends SubDependencyFactory {

        public KotlinxIoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.jetbrains.kotlinx:kotlinx-io-core)
         * with versionRef 'kotlinxIOCore'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("kotlinx.io.core");
        }

    }

    public static class KotlinxSerializationLibraryAccessors extends SubDependencyFactory {

        public KotlinxSerializationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for json (org.jetbrains.kotlinx:kotlinx-serialization-json)
         * with versionRef 'kotlinxSerializationJson'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJson() {
                return create("kotlinx.serialization.json");
        }

    }

    public static class KtorLibraryAccessors extends SubDependencyFactory {

        public KtorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for http (io.ktor:ktor-http)
         * with versionRef 'ktor'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getHttp() {
                return create("ktor.http");
        }

    }

    public static class LifecycleLibraryAccessors extends SubDependencyFactory {
        private final LifecycleLivedataLibraryAccessors laccForLifecycleLivedataLibraryAccessors = new LifecycleLivedataLibraryAccessors(owner);
        private final LifecycleViewmodelLibraryAccessors laccForLifecycleViewmodelLibraryAccessors = new LifecycleViewmodelLibraryAccessors(owner);

        public LifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at lifecycle.livedata
         */
        public LifecycleLivedataLibraryAccessors getLivedata() {
            return laccForLifecycleLivedataLibraryAccessors;
        }

        /**
         * Returns the group of libraries at lifecycle.viewmodel
         */
        public LifecycleViewmodelLibraryAccessors getViewmodel() {
            return laccForLifecycleViewmodelLibraryAccessors;
        }

    }

    public static class LifecycleLivedataLibraryAccessors extends SubDependencyFactory {

        public LifecycleLivedataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-livedata-ktx)
         * with versionRef 'lifecycleKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("lifecycle.livedata.ktx");
        }

    }

    public static class LifecycleViewmodelLibraryAccessors extends SubDependencyFactory {

        public LifecycleViewmodelLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-viewmodel-ktx)
         * with versionRef 'lifecycleKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("lifecycle.viewmodel.ktx");
        }

    }

    public static class Media3LibraryAccessors extends SubDependencyFactory {
        private final Media3DatasourceLibraryAccessors laccForMedia3DatasourceLibraryAccessors = new Media3DatasourceLibraryAccessors(owner);
        private final Media3ExoplayerLibraryAccessors laccForMedia3ExoplayerLibraryAccessors = new Media3ExoplayerLibraryAccessors(owner);

        public Media3LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for cast (androidx.media3:media3-cast)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCast() {
                return create("media3.cast");
        }

            /**
             * Creates a dependency provider for common (androidx.media3:media3-common)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCommon() {
                return create("media3.common");
        }

            /**
             * Creates a dependency provider for container (androidx.media3:media3-container)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getContainer() {
                return create("media3.container");
        }

            /**
             * Creates a dependency provider for session (androidx.media3:media3-session)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSession() {
                return create("media3.session");
        }

            /**
             * Creates a dependency provider for ui (androidx.media3:media3-ui)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getUi() {
                return create("media3.ui");
        }

        /**
         * Returns the group of libraries at media3.datasource
         */
        public Media3DatasourceLibraryAccessors getDatasource() {
            return laccForMedia3DatasourceLibraryAccessors;
        }

        /**
         * Returns the group of libraries at media3.exoplayer
         */
        public Media3ExoplayerLibraryAccessors getExoplayer() {
            return laccForMedia3ExoplayerLibraryAccessors;
        }

    }

    public static class Media3DatasourceLibraryAccessors extends SubDependencyFactory {

        public Media3DatasourceLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for cronet (androidx.media3:media3-datasource-cronet)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCronet() {
                return create("media3.datasource.cronet");
        }

            /**
             * Creates a dependency provider for okhttp (androidx.media3:media3-datasource-okhttp)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getOkhttp() {
                return create("media3.datasource.okhttp");
        }

    }

    public static class Media3ExoplayerLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public Media3ExoplayerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for exoplayer (androidx.media3:media3-exoplayer)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("media3.exoplayer");
        }

            /**
             * Creates a dependency provider for dash (androidx.media3:media3-exoplayer-dash)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDash() {
                return create("media3.exoplayer.dash");
        }

            /**
             * Creates a dependency provider for hls (androidx.media3:media3-exoplayer-hls)
         * with versionRef 'media3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getHls() {
                return create("media3.exoplayer.hls");
        }

    }

    public static class NavigationLibraryAccessors extends SubDependencyFactory {
        private final NavigationFragmentLibraryAccessors laccForNavigationFragmentLibraryAccessors = new NavigationFragmentLibraryAccessors(owner);
        private final NavigationUiLibraryAccessors laccForNavigationUiLibraryAccessors = new NavigationUiLibraryAccessors(owner);

        public NavigationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at navigation.fragment
         */
        public NavigationFragmentLibraryAccessors getFragment() {
            return laccForNavigationFragmentLibraryAccessors;
        }

        /**
         * Returns the group of libraries at navigation.ui
         */
        public NavigationUiLibraryAccessors getUi() {
            return laccForNavigationUiLibraryAccessors;
        }

    }

    public static class NavigationFragmentLibraryAccessors extends SubDependencyFactory {

        public NavigationFragmentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-fragment-ktx)
         * with versionRef 'navigationKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("navigation.fragment.ktx");
        }

    }

    public static class NavigationUiLibraryAccessors extends SubDependencyFactory {

        public NavigationUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-ui-ktx)
         * with versionRef 'navigationKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("navigation.ui.ktx");
        }

    }

    public static class NextlibLibraryAccessors extends SubDependencyFactory {

        public NextlibLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for media3ext (io.github.anilbeesetti:nextlib-media3ext)
         * with versionRef 'nextlibMedia3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMedia3ext() {
                return create("nextlib.media3ext");
        }

            /**
             * Creates a dependency provider for mediainfo (io.github.anilbeesetti:nextlib-mediainfo)
         * with versionRef 'nextlibMedia3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMediainfo() {
                return create("nextlib.mediainfo");
        }

    }

    public static class PaletteLibraryAccessors extends SubDependencyFactory {

        public PaletteLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.palette:palette-ktx)
         * with versionRef 'paletteKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("palette.ktx");
        }

    }

    public static class PreferenceLibraryAccessors extends SubDependencyFactory {

        public PreferenceLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.preference:preference-ktx)
         * with versionRef 'preferenceKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("preference.ktx");
        }

    }

    public static class PreviewseekbarLibraryAccessors extends SubDependencyFactory {

        public PreviewseekbarLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for media3 (com.github.rubensousa:previewseekbar-media3)
         * with versionRef 'previewseekbarMedia3'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMedia3() {
                return create("previewseekbar.media3");
        }

    }

    public static class QrcodeLibraryAccessors extends SubDependencyFactory {

        public QrcodeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for kotlin (io.github.g0dkar:qrcode-kotlin)
         * with versionRef 'qrcodeKotlin'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKotlin() {
                return create("qrcode.kotlin");
        }

    }

    public static class WorkLibraryAccessors extends SubDependencyFactory {
        private final WorkRuntimeLibraryAccessors laccForWorkRuntimeLibraryAccessors = new WorkRuntimeLibraryAccessors(owner);

        public WorkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at work.runtime
         */
        public WorkRuntimeLibraryAccessors getRuntime() {
            return laccForWorkRuntimeLibraryAccessors;
        }

    }

    public static class WorkRuntimeLibraryAccessors extends SubDependencyFactory {

        public WorkRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.work:work-runtime-ktx)
         * with versionRef 'workRuntimeKtx'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("work.runtime.ktx");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final DesugarVersionAccessors vaccForDesugarVersionAccessors = new DesugarVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: activityKtx (1.13.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getActivityKtx() { return getVersion("activityKtx"); }

            /**
             * Returns the version associated to this alias: androidGradlePlugin (9.1.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidGradlePlugin() { return getVersion("androidGradlePlugin"); }

            /**
             * Returns the version associated to this alias: animeDb (1.0.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAnimeDb() { return getVersion("animeDb"); }

            /**
             * Returns the version associated to this alias: annotation (1.10.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAnnotation() { return getVersion("annotation"); }

            /**
             * Returns the version associated to this alias: appcompat (1.7.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAppcompat() { return getVersion("appcompat"); }

            /**
             * Returns the version associated to this alias: biometric (1.4.0-alpha07)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBiometric() { return getVersion("biometric"); }

            /**
             * Returns the version associated to this alias: buildkonfigGradlePlugin (0.21.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBuildkonfigGradlePlugin() { return getVersion("buildkonfigGradlePlugin"); }

            /**
             * Returns the version associated to this alias: coil ({strictly 3.3.0})
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCoil() { return getVersion("coil"); }

            /**
             * Returns the version associated to this alias: colorpicker (6b46b49)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getColorpicker() { return getVersion("colorpicker"); }

            /**
             * Returns the version associated to this alias: compileSdk (37)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompileSdk() { return getVersion("compileSdk"); }

            /**
             * Returns the version associated to this alias: conscryptAndroid ({strictly 2.5.2})
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getConscryptAndroid() { return getVersion("conscryptAndroid"); }

            /**
             * Returns the version associated to this alias: constraintlayout (2.2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getConstraintlayout() { return getVersion("constraintlayout"); }

            /**
             * Returns the version associated to this alias: coreKtx (1.18.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCoreKtx() { return getVersion("coreKtx"); }

            /**
             * Returns the version associated to this alias: cryptography (0.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCryptography() { return getVersion("cryptography"); }

            /**
             * Returns the version associated to this alias: dokkaGradlePlugin (2.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getDokkaGradlePlugin() { return getVersion("dokkaGradlePlugin"); }

            /**
             * Returns the version associated to this alias: espressoCore (3.7.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getEspressoCore() { return getVersion("espressoCore"); }

            /**
             * Returns the version associated to this alias: fragmentKtx (1.8.9)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFragmentKtx() { return getVersion("fragmentKtx"); }

            /**
             * Returns the version associated to this alias: instancioCore (5.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getInstancioCore() { return getVersion("instancioCore"); }

            /**
             * Returns the version associated to this alias: jacksonModuleKotlin ({strictly 2.13.1})
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJacksonModuleKotlin() { return getVersion("jacksonModuleKotlin"); }

            /**
             * Returns the version associated to this alias: jdkToolchain (17)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJdkToolchain() { return getVersion("jdkToolchain"); }

            /**
             * Returns the version associated to this alias: json (20260522)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJson() { return getVersion("json"); }

            /**
             * Returns the version associated to this alias: jsoup (1.22.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJsoup() { return getVersion("jsoup"); }

            /**
             * Returns the version associated to this alias: junit (4.13.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("junit"); }

            /**
             * Returns the version associated to this alias: junitKtx (1.3.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunitKtx() { return getVersion("junitKtx"); }

            /**
             * Returns the version associated to this alias: junitVersion (1.3.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunitVersion() { return getVersion("junitVersion"); }

            /**
             * Returns the version associated to this alias: juniversalchardet (2.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJuniversalchardet() { return getVersion("juniversalchardet"); }

            /**
             * Returns the version associated to this alias: jvmTarget (1.8)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJvmTarget() { return getVersion("jvmTarget"); }

            /**
             * Returns the version associated to this alias: kotlinGradlePlugin (2.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinGradlePlugin() { return getVersion("kotlinGradlePlugin"); }

            /**
             * Returns the version associated to this alias: kotlinxAtomicfu (0.33.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxAtomicfu() { return getVersion("kotlinxAtomicfu"); }

            /**
             * Returns the version associated to this alias: kotlinxCollectionsImmutable (0.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxCollectionsImmutable() { return getVersion("kotlinxCollectionsImmutable"); }

            /**
             * Returns the version associated to this alias: kotlinxCoroutines (1.11.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxCoroutines() { return getVersion("kotlinxCoroutines"); }

            /**
             * Returns the version associated to this alias: kotlinxDatetime (0.8.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxDatetime() { return getVersion("kotlinxDatetime"); }

            /**
             * Returns the version associated to this alias: kotlinxIOCore (0.9.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxIOCore() { return getVersion("kotlinxIOCore"); }

            /**
             * Returns the version associated to this alias: kotlinxSerializationJson (1.11.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxSerializationJson() { return getVersion("kotlinxSerializationJson"); }

            /**
             * Returns the version associated to this alias: ksoup (0.2.6)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKsoup() { return getVersion("ksoup"); }

            /**
             * Returns the version associated to this alias: ktor (3.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKtor() { return getVersion("ktor"); }

            /**
             * Returns the version associated to this alias: lifecycleKtx (2.10.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLifecycleKtx() { return getVersion("lifecycleKtx"); }

            /**
             * Returns the version associated to this alias: material (1.14.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMaterial() { return getVersion("material"); }

            /**
             * Returns the version associated to this alias: media3 (1.9.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMedia3() { return getVersion("media3"); }

            /**
             * Returns the version associated to this alias: minSdk (23)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMinSdk() { return getVersion("minSdk"); }

            /**
             * Returns the version associated to this alias: navigationKtx (2.9.8)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNavigationKtx() { return getVersion("navigationKtx"); }

            /**
             * Returns the version associated to this alias: newpipeextractor (v0.26.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNewpipeextractor() { return getVersion("newpipeextractor"); }

            /**
             * Returns the version associated to this alias: nextlibMedia3 (1.9.3-0.12.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNextlibMedia3() { return getVersion("nextlibMedia3"); }

            /**
             * Returns the version associated to this alias: nicehttp (0.4.18)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNicehttp() { return getVersion("nicehttp"); }

            /**
             * Returns the version associated to this alias: overlappingpanels (0.1.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getOverlappingpanels() { return getVersion("overlappingpanels"); }

            /**
             * Returns the version associated to this alias: paletteKtx (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPaletteKtx() { return getVersion("paletteKtx"); }

            /**
             * Returns the version associated to this alias: preferenceKtx (1.2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPreferenceKtx() { return getVersion("preferenceKtx"); }

            /**
             * Returns the version associated to this alias: previewseekbarMedia3 (1.1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPreviewseekbarMedia3() { return getVersion("previewseekbarMedia3"); }

            /**
             * Returns the version associated to this alias: qrcodeKotlin (4.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getQrcodeKotlin() { return getVersion("qrcodeKotlin"); }

            /**
             * Returns the version associated to this alias: rhino ({strictly 1.8.1})
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRhino() { return getVersion("rhino"); }

            /**
             * Returns the version associated to this alias: safefile (0.0.8)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSafefile() { return getVersion("safefile"); }

            /**
             * Returns the version associated to this alias: shimmer (0.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getShimmer() { return getVersion("shimmer"); }

            /**
             * Returns the version associated to this alias: targetSdk (36)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTargetSdk() { return getVersion("targetSdk"); }

            /**
             * Returns the version associated to this alias: torrentserver (7861970)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTorrentserver() { return getVersion("torrentserver"); }

            /**
             * Returns the version associated to this alias: tvprovider (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTvprovider() { return getVersion("tvprovider"); }

            /**
             * Returns the version associated to this alias: versionCode (68)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersionCode() { return getVersion("versionCode"); }

            /**
             * Returns the version associated to this alias: versionName (4.8.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersionName() { return getVersion("versionName"); }

            /**
             * Returns the version associated to this alias: video (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVideo() { return getVersion("video"); }

            /**
             * Returns the version associated to this alias: workRuntimeKtx (2.11.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getWorkRuntimeKtx() { return getVersion("workRuntimeKtx"); }

            /**
             * Returns the version associated to this alias: zipline (1.27.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getZipline() { return getVersion("zipline"); }

        /**
         * Returns the group of versions at versions.desugar
         */
        public DesugarVersionAccessors getDesugar() {
            return vaccForDesugarVersionAccessors;
        }

    }

    public static class DesugarVersionAccessors extends VersionFactory  {

        private final DesugarJdkVersionAccessors vaccForDesugarJdkVersionAccessors = new DesugarJdkVersionAccessors(providers, config);
        public DesugarVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.desugar.jdk
         */
        public DesugarJdkVersionAccessors getJdk() {
            return vaccForDesugarJdkVersionAccessors;
        }

    }

    public static class DesugarJdkVersionAccessors extends VersionFactory  {

        private final DesugarJdkLibsVersionAccessors vaccForDesugarJdkLibsVersionAccessors = new DesugarJdkLibsVersionAccessors(providers, config);
        public DesugarJdkVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.desugar.jdk.libs
         */
        public DesugarJdkLibsVersionAccessors getLibs() {
            return vaccForDesugarJdkLibsVersionAccessors;
        }

    }

    public static class DesugarJdkLibsVersionAccessors extends VersionFactory  {

        public DesugarJdkLibsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: desugar.jdk.libs.nio (2.1.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNio() { return getVersion("desugar.jdk.libs.nio"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

            /**
             * Creates a dependency bundle provider for coil which is an aggregate for the following dependencies:
             * <ul>
             *    <li>io.coil-kt.coil3:coil</li>
             *    <li>io.coil-kt.coil3:coil-network-okhttp</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getCoil() {
                return createBundle("coil");
            }

            /**
             * Creates a dependency bundle provider for cryptography which is an aggregate for the following dependencies:
             * <ul>
             *    <li>dev.whyoleg.cryptography:cryptography-core</li>
             *    <li>dev.whyoleg.cryptography:cryptography-provider-optimal</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getCryptography() {
                return createBundle("cryptography");
            }

            /**
             * Creates a dependency bundle provider for lifecycle which is an aggregate for the following dependencies:
             * <ul>
             *    <li>androidx.lifecycle:lifecycle-livedata-ktx</li>
             *    <li>androidx.lifecycle:lifecycle-viewmodel-ktx</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getLifecycle() {
                return createBundle("lifecycle");
            }

            /**
             * Creates a dependency bundle provider for media3 which is an aggregate for the following dependencies:
             * <ul>
             *    <li>androidx.media3:media3-cast</li>
             *    <li>androidx.media3:media3-common</li>
             *    <li>androidx.media3:media3-container</li>
             *    <li>androidx.media3:media3-datasource-cronet</li>
             *    <li>androidx.media3:media3-datasource-okhttp</li>
             *    <li>androidx.media3:media3-exoplayer</li>
             *    <li>androidx.media3:media3-exoplayer-dash</li>
             *    <li>androidx.media3:media3-exoplayer-hls</li>
             *    <li>androidx.media3:media3-session</li>
             *    <li>androidx.media3:media3-ui</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getMedia3() {
                return createBundle("media3");
            }

            /**
             * Creates a dependency bundle provider for navigation which is an aggregate for the following dependencies:
             * <ul>
             *    <li>androidx.navigation:navigation-fragment-ktx</li>
             *    <li>androidx.navigation:navigation-ui-ktx</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getNavigation() {
                return createBundle("navigation");
            }

            /**
             * Creates a dependency bundle provider for nextlib which is an aggregate for the following dependencies:
             * <ul>
             *    <li>io.github.anilbeesetti:nextlib-media3ext</li>
             *    <li>io.github.anilbeesetti:nextlib-mediainfo</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getNextlib() {
                return createBundle("nextlib");
            }

    }

    public static class PluginAccessors extends PluginFactory {
        private final AndroidPluginAccessors paccForAndroidPluginAccessors = new AndroidPluginAccessors(providers, config);
        private final KotlinPluginAccessors paccForKotlinPluginAccessors = new KotlinPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for buildkonfig to the plugin id 'com.codingfeline.buildkonfig'
             * with versionRef 'buildkonfigGradlePlugin'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getBuildkonfig() { return createPlugin("buildkonfig"); }

            /**
             * Creates a plugin provider for dokka to the plugin id 'org.jetbrains.dokka'
             * with versionRef 'dokkaGradlePlugin'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getDokka() { return createPlugin("dokka"); }

        /**
         * Returns the group of plugins at plugins.android
         */
        public AndroidPluginAccessors getAndroid() {
            return paccForAndroidPluginAccessors;
        }

        /**
         * Returns the group of plugins at plugins.kotlin
         */
        public KotlinPluginAccessors getKotlin() {
            return paccForKotlinPluginAccessors;
        }

    }

    public static class AndroidPluginAccessors extends PluginFactory {
        private final AndroidMultiplatformPluginAccessors paccForAndroidMultiplatformPluginAccessors = new AndroidMultiplatformPluginAccessors(providers, config);

        public AndroidPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for android.application to the plugin id 'com.android.application'
             * with versionRef 'androidGradlePlugin'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getApplication() { return createPlugin("android.application"); }

            /**
             * Creates a plugin provider for android.lint to the plugin id 'com.android.lint'
             * with versionRef 'androidGradlePlugin'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getLint() { return createPlugin("android.lint"); }

        /**
         * Returns the group of plugins at plugins.android.multiplatform
         */
        public AndroidMultiplatformPluginAccessors getMultiplatform() {
            return paccForAndroidMultiplatformPluginAccessors;
        }

    }

    public static class AndroidMultiplatformPluginAccessors extends PluginFactory {

        public AndroidMultiplatformPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for android.multiplatform.library to the plugin id 'com.android.kotlin.multiplatform.library'
             * with versionRef 'androidGradlePlugin'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getLibrary() { return createPlugin("android.multiplatform.library"); }

    }

    public static class KotlinPluginAccessors extends PluginFactory {

        public KotlinPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.jvm to the plugin id 'org.jetbrains.kotlin.jvm'
             * with versionRef 'kotlinGradlePlugin'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJvm() { return createPlugin("kotlin.jvm"); }

            /**
             * Creates a plugin provider for kotlin.multiplatform to the plugin id 'org.jetbrains.kotlin.multiplatform'
             * with versionRef 'kotlinGradlePlugin'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getMultiplatform() { return createPlugin("kotlin.multiplatform"); }

            /**
             * Creates a plugin provider for kotlin.serialization to the plugin id 'org.jetbrains.kotlin.plugin.serialization'
             * with versionRef 'kotlinGradlePlugin'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getSerialization() { return createPlugin("kotlin.serialization"); }

    }

}
