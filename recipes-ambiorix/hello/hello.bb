SUMMARY = "AMX hello data model example"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://hello.odl file://hello.service"
S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "hello.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

RDEPENDS:${PN} += "amxrt mod-amxb-ubus ubusd-systemd"

do_install() {
    install -d ${D}${sysconfdir}/amx/hello
    install -m 0644 ${WORKDIR}/hello.odl ${D}${sysconfdir}/amx/hello/
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/hello.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${sysconfdir}/amx/hello/hello.odl"
