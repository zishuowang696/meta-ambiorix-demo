SUMMARY = "Ambiorix generic data containers"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f8eaa5ae328592b1346db42ce2936d90"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/libraries/libamxc.git;protocol=https;nobranch=1"
SRCREV = "1623d9fc299c03c9d47adb95a884a0309cabf4eb"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

do_compile() {
    oe_runmake
}

FILES:${PN} += "${libdir}/libamxc.so.*"
FILES:${PN}-dev += "${libdir}/libamxc.so ${includedir}/amxc/*.h"
FILES:${PN}-staticdev += "${libdir}/libamxc.a"
