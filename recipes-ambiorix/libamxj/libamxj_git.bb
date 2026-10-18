SUMMARY = "Ambiorix JSON parser and generator using yajl"
LICENSE = "SAH & BSD-2-Clause-Patent"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f8eaa5ae328592b1346db42ce2936d90"

SRC_URI = "git://gitlab.com/prpl-foundation/components/ambiorix/libraries/libamxj.git;protocol=https;nobranch=1"
SRCREV = "347830147e4ea986f795666af4222a5a6527cb7b"
S = "${WORKDIR}/git"

inherit pkgconfig config-amx

DEPENDS = "libamxc yajl"
RDEPENDS:${PN} += "libamxc yajl"

do_compile() {
    oe_runmake
}

FILES:${PN} += "${libdir}/libamxj.so.*"
FILES:${PN}-dev += "${libdir}/libamxj.so ${includedir}/amxj/*.h"
FILES:${PN}-staticdev += "${libdir}/libamxj.a"
