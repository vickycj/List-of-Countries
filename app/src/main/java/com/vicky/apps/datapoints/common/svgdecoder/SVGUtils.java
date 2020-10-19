package com.vicky.apps.datapoints.common.svgdecoder;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;

import com.bumptech.glide.RequestBuilder;
import com.vicky.apps.datapoints.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class SVGUtils {


    public static RequestBuilder<PictureDrawable> getSVGRequestBuilder(Context context) {
        return GlideApp.with(context)
                .as(PictureDrawable.class)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .listener(new SvgSoftwareLayerSetter());
    }
}
