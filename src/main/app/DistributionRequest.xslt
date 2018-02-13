<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:doc="http://local.topdanmark.dk/DocDistribution/" version="1.0">
   <xsl:output method="xml" encoding="UTF-8" indent="yes" />
   <xsl:param name="customerId" />
   <xsl:param name="name" />
   <xsl:param name="street_1" />
   <xsl:param name="street_2" />
   <xsl:param name="zipcode" />
   <xsl:param name="city" />
   <xsl:param name="country" />
   <xsl:param name="policynumber" />
   <xsl:param name="policytype" />
   <xsl:param name="price" />
   <xsl:template match="/">
     <doc:distribution_request>
         <customerId>
         	<xsl:value-of select="$customerId" />
         </customerId>
         <name>
         	<xsl:value-of select="$name" />
         </name>
         <street_1>
         	<xsl:value-of select="$street_1" />
         </street_1>
         <street_2>
         	<xsl:value-of select="$street_2" />
         </street_2>
         <zipcode>
         	<xsl:value-of select="$zipcode" />
         </zipcode>
         <city>
         	<xsl:value-of select="$city" />
         </city>
         <country>
         	<xsl:value-of select="$country" />
         </country>
         <policynumber>
         	<xsl:value-of select="$policynumber" />
         </policynumber>
         <policytype>
         	<xsl:value-of select="$policytype" />
         </policytype>
         <price>
         	<xsl:value-of select="$price" />
         </price>
       </doc:distribution_request>
   </xsl:template>
</xsl:stylesheet>