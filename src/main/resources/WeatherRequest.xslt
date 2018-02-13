<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:web="http://www.webserviceX.NET" version="1.0">
   <xsl:output method="xml" encoding="UTF-8" indent="yes" />
   <xsl:param name="countryName" />
   <xsl:template match="/">
      <web:GetCitiesByCountry>
         <web:CountryName>
            <xsl:value-of select="$countryName" />
         </web:CountryName>
      </web:GetCitiesByCountry>
   </xsl:template>
</xsl:stylesheet>