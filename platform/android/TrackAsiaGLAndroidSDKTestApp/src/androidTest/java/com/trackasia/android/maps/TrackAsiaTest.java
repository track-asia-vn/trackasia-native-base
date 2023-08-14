package com.trackasia.android.maps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import androidx.test.annotation.UiThreadTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.trackasia.android.AppCenter;
import com.trackasia.android.TrackAsia;
import com.trackasia.android.exceptions.TrackAsiaConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class TrackAsiaTest extends AppCenter {

  private static final String API_KEY = "pk.0000000001";
  private static final String API_KEY_2 = "pk.0000000002";

  private String realToken;

  @Before
  public void setup() {
    realToken = TrackAsia.getApiKey();
  }

  @Test
  @UiThreadTest
  public void testConnected() {
    assertTrue(TrackAsia.isConnected());

    // test manual connectivity
    TrackAsia.setConnected(true);
    assertTrue(TrackAsia.isConnected());
    TrackAsia.setConnected(false);
    assertFalse(TrackAsia.isConnected());

    // reset to Android connectivity
    TrackAsia.setConnected(null);
    assertTrue(TrackAsia.isConnected());
  }

  @Test
  @UiThreadTest
  public void setApiKey() {
    TrackAsia.setApiKey(API_KEY);
    assertSame(API_KEY, TrackAsia.getApiKey());
    TrackAsia.setApiKey(API_KEY_2);
    assertSame(API_KEY_2, TrackAsia.getApiKey());
  }

  @Test
  @UiThreadTest
  public void setNullApiKey() {
    assertThrows(TrackAsiaConfigurationException.class, () -> TrackAsia.setApiKey(null));
  }

  @After
  public void tearDown() {
    TrackAsia.setApiKey(realToken);
  }
}