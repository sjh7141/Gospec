<template>
    <div>

        <!-- Services ================================================== -->
        <section id="nino-services">
            <div class="container">
                <h2 class="nino-sectionHeading">
                    <span class="nino-subHeading">We work with</span>
                    Amazing Services
                </h2>
                <div class="sectionContent">
                    <div class="fw row">
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                                <i class="mdi mdi-apple-keyboard-command nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">Photography</h4>
                                    <p>Photography is the science, art and practice of creating durable images by
                                        recording light or other electromagnetic radiation</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                                <i class="mdi mdi-blender nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">Web Design</h4>
                                    <p>Web design encompasses many different skills and disciplines in the
                                        production and maintenance of websites.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                                <i class="mdi mdi-bookmark-plus-outline nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">Creativity</h4>
                                    <p>Creativity is a phenomenon whereby something new and somehow valuable is
                                        formed. The created item may be intangible or a physical object.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                                <i class="mdi mdi-buffer nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">seo</h4>
                                    <p>Search engine optimization (SEO) is the process of affecting the visibility
                                        of a website or a web page in a web search engine's unpaid results.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                                <i class="mdi mdi-desktop-mac nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">Css/Html</h4>
                                    <p>HyperText Markup Language, commonly abbreviated as HTML, is the standard
                                        markup language used to create web pages.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                                <i class="mdi mdi-diamond nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">digital</h4>
                                    <p>With digital signals, system noise, provided it is not too great, will not
                                        change the quantification whereas with signals handled using analog processing.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--/#nino-services-->
    </div>
</template>

<script>
    export default {}
</script>

<style scoped>

/* =============== SECTION SERVICES =============== */
#nino-services .nino-icon {
	font-size: 30px;
	margin-right: auto;
  margin-left: auto;
	color: #95e1d3;
}
#nino-services .item:not(:nth-last-child(-n+3)) {
	border-bottom: 1px solid #e5e5e5;
	margin-bottom: 50px;
	padding-bottom: 40px;
}
#nino-services .nino-serviceTitle {
	font-weight: 500;
	font-size: 14px;
}
.item {
  padding: 40px;
}
i {
  margin-right: 0;
}
</style>