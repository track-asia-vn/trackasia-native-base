#ifndef QTRACKASIAGL_TYPES_H
#define QTRACKASIAGL_TYPES_H

#include <QColor>
#include <QPair>
#include <QString>
#include <QVariant>
#include <QVector>

#include "export.hpp"

namespace QTrackAsiaGL {

typedef QPair<double, double> Coordinate;
typedef QPair<Coordinate, double> CoordinateZoom;
typedef QPair<double, double> ProjectedMeters;

typedef QVector<Coordinate> Coordinates;
typedef QVector<Coordinates> CoordinatesCollection;

typedef QVector<CoordinatesCollection> CoordinatesCollections;

struct Q_TRACKASIAGL_EXPORT Feature {
    enum Type {
        PointType = 1,
        LineStringType,
        PolygonType
    };

    /*! Class constructor. */
    Feature(Type type_ = PointType,
            const CoordinatesCollections& geometry_ = CoordinatesCollections(),
            const QVariantMap& properties_ = QVariantMap(),
            const QVariant& id_ = QVariant())
        : type(type_),
          geometry(geometry_),
          properties(properties_),
          id(id_) {}

    Type type;
    CoordinatesCollections geometry;
    QVariantMap properties;
    QVariant id;
};

struct Q_TRACKASIAGL_EXPORT ShapeAnnotationGeometry {
    enum Type {
        LineStringType = 1,
        PolygonType,
        MultiLineStringType,
        MultiPolygonType
    };

    /*! Class constructor. */
    ShapeAnnotationGeometry(Type type_ = LineStringType,
                            const CoordinatesCollections& geometry_ = CoordinatesCollections())
        : type(type_),
          geometry(geometry_) {}

    Type type;
    CoordinatesCollections geometry;
};

struct Q_TRACKASIAGL_EXPORT SymbolAnnotation {
    Coordinate geometry;
    QString icon;
};

struct Q_TRACKASIAGL_EXPORT LineAnnotation {
    /*! Class constructor. */
    LineAnnotation(const ShapeAnnotationGeometry& geometry_ = ShapeAnnotationGeometry(),
                   float opacity_ = 1.0f,
                   float width_ = 1.0f,
                   const QColor& color_ = Qt::black)
        : geometry(geometry_),
          opacity(opacity_),
          width(width_),
          color(color_) {}

    ShapeAnnotationGeometry geometry;
    float opacity;
    float width;
    QColor color;
};

struct Q_TRACKASIAGL_EXPORT FillAnnotation {
    /*! Class constructor. */
    FillAnnotation(const ShapeAnnotationGeometry& geometry_ = ShapeAnnotationGeometry(),
                   float opacity_ = 1.0f,
                   const QColor& color_ = Qt::black,
                   const QVariant& outlineColor_ = QVariant())
        : geometry(geometry_),
          opacity(opacity_),
          color(color_),
          outlineColor(outlineColor_) {}

    ShapeAnnotationGeometry geometry;
    float opacity;
    QColor color;
    QVariant outlineColor;
};

typedef QVariant Annotation;
typedef quint32 AnnotationID;
typedef QVector<AnnotationID> AnnotationIDs;

struct Q_TRACKASIAGL_EXPORT CameraOptions {
    QVariant center;  // Coordinate
    QVariant anchor;  // QPointF
    QVariant zoom;    // double
    QVariant bearing; // double
    QVariant pitch;   // double
};

// This struct is a 1:1 copy of mbgl::CustomLayerRenderParameters.
struct Q_TRACKASIAGL_EXPORT CustomLayerRenderParameters {
    double width;
    double height;
    double latitude;
    double longitude;
    double zoom;
    double bearing;
    double pitch;
    double fieldOfView;
};

class Q_TRACKASIAGL_EXPORT CustomLayerHostInterface {
public:
    virtual ~CustomLayerHostInterface() = default;
    virtual void initialize() = 0;
    virtual void render(const CustomLayerRenderParameters&) = 0;
    virtual void deinitialize() = 0;
};

} // namespace QTrackAsiaGL

Q_DECLARE_METATYPE(QTrackAsiaGL::Coordinate);
Q_DECLARE_METATYPE(QTrackAsiaGL::Coordinates);
Q_DECLARE_METATYPE(QTrackAsiaGL::CoordinatesCollection);
Q_DECLARE_METATYPE(QTrackAsiaGL::CoordinatesCollections);
Q_DECLARE_METATYPE(QTrackAsiaGL::Feature);

Q_DECLARE_METATYPE(QTrackAsiaGL::SymbolAnnotation);
Q_DECLARE_METATYPE(QTrackAsiaGL::ShapeAnnotationGeometry);
Q_DECLARE_METATYPE(QTrackAsiaGL::LineAnnotation);
Q_DECLARE_METATYPE(QTrackAsiaGL::FillAnnotation);

#endif // QTRACKASIAGL_TYPES_H
