package com.trackasia.android;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import com.trackasia.android.exceptions.TrackAsiaConfigurationException;
import com.trackasia.android.maps.MapView;
import com.trackasia.android.utils.ConfigUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackAsiaTest {

  private Context context;
  private Context appContext;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void before() {
    context = mock(Context.class);
    appContext = mock(Context.class);
    when(context.getApplicationContext()).thenReturn(appContext);
  }

  @Test
  public void testGetApiKey() {
    final String apiKey = "pk.0000000001";
    TrackAsiaInjector.inject(context, apiKey, ConfigUtils.getMockedOptions());
    assertSame(apiKey, TrackAsia.getApiKey());
  }

  @Test
  public void testApplicationContext() {
    TrackAsiaInjector.inject(context, "pk.0000000001", ConfigUtils.getMockedOptions());
    assertNotNull(TrackAsia.getApplicationContext());
    assertNotEquals(context, appContext);
    assertEquals(appContext, appContext);
  }

  @Test
  public void testPlainTokenValid() {
    assertTrue(TrackAsia.isApiKeyValid("apiKey"));
  }

  @Test
  public void testEmptyToken() {
    assertFalse(TrackAsia.isApiKeyValid(""));
  }

  @Test
  public void testNullToken() {
    assertFalse(TrackAsia.isApiKeyValid(null));
  }

  @Test
  public void testNoInstance() {
    DisplayMetrics displayMetrics = mock(DisplayMetrics.class);
    Resources resources = mock(Resources.class);
    when(resources.getDisplayMetrics()).thenReturn(displayMetrics);
    when(context.getResources()).thenReturn(resources);
    TypedArray typedArray = mock(TypedArray.class);
    when(context.obtainStyledAttributes(nullable(AttributeSet.class), any(int[].class), anyInt(), anyInt()))
      .thenReturn(typedArray);

    expectedException.expect(TrackAsiaConfigurationException.class);
    expectedException.expectMessage(
      "\nUsing MapView requires calling TrackAsia.getInstance(Context context, String apiKey,"
              + " WellKnownTileServer wellKnownTileServer) before inflating or creating the view."
    );
    new MapView(context);
  }

  @After
  public void after() {
    TrackAsiaInjector.clear();
  }
}
