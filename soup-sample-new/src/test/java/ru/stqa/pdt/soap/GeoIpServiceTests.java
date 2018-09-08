package ru.stqa.pdt.soap;

import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {
  @Test
  public void testGeoIpService() {
    BBHLocation userLocation = new GeoIP().getGeoIPSoap12().getUserLocation("94.180.118.208");
    assertEquals(userLocation.getCountryCode(), "US");
  }
}
