SUMMARY = "Ambiorix data model runtime"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bb005226550a047f93eb1924998c2c2f"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/applications/amxrt.git;protocol=https;nobranch=1"
SRCREV = "c006ebc95da1b19bf74d59521b03f88e12e6556b"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx update-rc.d

INITSCRIPT_NAME = "amx-shutdown-wait"
INITSCRIPT_PARAMS = "start 90 2 3 4 5 . stop 90 0 1 6 ."

DEPENDS = "libamxc libamxj libamxp libamxd libamxo libamxb libevent libcap-ng libamxrt"
RDEPENDS:${PN} += "libamxc libamxj libamxp libamxd libamxo libamxb libevent libcap-ng libamxrt"

do_compile() {
    oe_runmake
}

FILES:${PN} += "${bindir}/amxrt"
FILES:${PN} += "${libdir}/amx/scripts/amx_init_functions.sh"
FILES:${PN} += "${sysconfdir}/init.d/amx-shutdown-wait"
