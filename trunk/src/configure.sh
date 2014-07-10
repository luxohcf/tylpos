#!/bin/sh


DIRNAME=`dirname $0`

CP=$DIRNAME/tlpos.jar

CP=$CP:$DIRNAME/locales/
CP=$CP:$DIRNAME/lib/substance.jar


java -cp $CP -Djava.util.logging.config.file=$DIRNAME/logging.properties -Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel com.openbravo.pos.config.JFrmConfig
