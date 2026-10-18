LIBDIR ?= "${libdir}"
INCLUDEDIR ?= "${includedir}"
INITDIR ?= "${sysconfdir}/init.d"

EXTRA_OEMAKE += "DEST=${D} \
                 D=${D}${base_prefix} \
                 PREFIX=${prefix} \
                 LIBDIR=${libdir} \
                 BINDIR=${bindir} \
                 INCLUDEDIR=${includedir} \
                 "

do_install() {
    oe_runmake install
}
