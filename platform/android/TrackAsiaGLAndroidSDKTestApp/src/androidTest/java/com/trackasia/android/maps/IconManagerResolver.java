package com.trackasia.android.maps;

import com.trackasia.android.annotations.Icon;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class IconManagerResolver {

  private IconManager iconManager;

  public IconManagerResolver(TrackAsiaMap trackasiaMap) {
    try {
      Field annotationManagerField = TrackAsiaMap.class.getDeclaredField("annotationManager");
      annotationManagerField.setAccessible(true);
      AnnotationManager annotationManager = (AnnotationManager) annotationManagerField.get(trackasiaMap);

      Field iconManagerField = AnnotationManager.class.getDeclaredField("iconManager");
      iconManagerField.setAccessible(true);
      iconManager = (IconManager) iconManagerField.get(annotationManager);
    } catch (Exception exception) {
      Timber.e(exception, "Could not create IconManagerResolver, unable to reflect.");
    }
  }

  @SuppressWarnings("unchecked")
  public Map<Icon, Integer> getIconMap() {
    try {
      Field field = IconManager.class.getDeclaredField("iconMap");
      field.setAccessible(true);
      return (Map<Icon, Integer>) field.get(iconManager);
    } catch (NoSuchFieldException exception) {
      Timber.e(exception, "Could not getIconMap, unable to reflect.");
    } catch (IllegalAccessException exception) {
      Timber.e(exception, "Could not getIconMap, unable to reflect.");
    }
    return new HashMap<>();
  }
}
