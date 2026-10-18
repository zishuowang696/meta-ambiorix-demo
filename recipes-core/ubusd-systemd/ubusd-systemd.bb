SUMMARY = "systemd unit for ubusd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://ubusd.service"
S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "ubusd.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

RDEPENDS:${PN} += "ubusd"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/ubusd.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${systemd_system_unitdir}/ubusd.service"
