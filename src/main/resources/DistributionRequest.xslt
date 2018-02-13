<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:doc="http://local.topdanmark.dk/DocDistribution/" version="1.0">
   <xsl:output method="xml" encoding="UTF-8" indent="yes" />
   <xsl:param name="customerId" />
   <xsl:template match="/">
     <doc:distribution_request>
         <customerId>
         	<xsl:value-of select="$customerId" />
         </customerId>
       </doc:distribution_request>
   </xsl:template>
</xsl:stylesheet>