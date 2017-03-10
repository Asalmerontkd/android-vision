package io.mariachi.allianzvision.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import io.mariachi.allianzvision.R;

/**
 * Created by antonio on 4/03/17.
 */

public class MarcoVertical extends View {
    private Paint paint = new Paint();

    public MarcoVertical(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint myPaint = new Paint();

        int regx = canvas.getWidth() / 9; //rejilla de 9
        int ancho = regx * 6;
        int canty = canvas.getHeight() / regx;
        int regy = canvas.getHeight() / canty; // rejilla alto
        int alto = regy * 9;

        int espacioy = ((canvas.getHeight() - alto) / 2)  - (regy / 2);
        int espaciox = (canvas.getWidth() - ancho) / 2;

        int barrax = ancho / 3;
        int barray = alto / 3;

        Utils.x = barrax;
        Utils.y = barray;
        Utils.alto = alto;
        Utils.ancho = ancho;
        Utils.rejilla = 9;
        Utils.constanteX = 6;
        Utils.constanteY = 9;

        Paint myGrayPaint = new Paint();
        myGrayPaint.setColor(getResources().getColor(R.color.black));
        myGrayPaint.setAlpha(35);

        canvas.drawRect(0,0,canvas.getWidth(),espacioy, myGrayPaint);
        canvas.drawRect(0, espacioy, espaciox, canvas.getHeight() , myGrayPaint);
        canvas.drawRect(espaciox, alto + espacioy, canvas.getWidth(), canvas.getHeight(), myGrayPaint);
        canvas.drawRect(ancho + espaciox, espacioy, canvas.getWidth(), alto + espacioy, myGrayPaint);

        //myPaint.setColor(getResources().getColor(R.color.whiteText));
        //myPaint.setStrokeWidth(2);

        //Lineas verticales
        //canvas.drawLine(espaciox + barrax, espacioy, espaciox +barrax, alto + espacioy, myPaint);
        //canvas.drawLine(espaciox + (barrax*2), espacioy, espaciox +(barrax*2), alto + espacioy, myPaint);
        //Lineas Horizontales
        //canvas.drawLine(espaciox, espacioy + barray, ancho + espaciox, espacioy + barray, myPaint);
        //canvas.drawLine(espaciox, espacioy + (barray*2), ancho + espaciox, espacioy + (barray*2), myPaint);

        myPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        myPaint.setStrokeWidth(10);

        canvas.drawPoint(espaciox, espacioy, myPaint); //punto superior izquierdo
        canvas.drawPoint(ancho + espaciox, espacioy, myPaint); //punto superior derecho
        canvas.drawPoint(espaciox, alto + espacioy, myPaint); //punto inferior izquierdo
        canvas.drawPoint(ancho + espaciox, espacioy + alto, myPaint); //punto inferior derecho

        canvas.drawLine(espaciox, espacioy, espaciox + (barrax / 2), espacioy, myPaint);
        canvas.drawLine((ancho + espaciox) - (barrax / 2) , espacioy, ancho + espaciox, espacioy, myPaint);

        canvas.drawLine(espaciox, alto + espacioy, espaciox + (barrax / 2), espacioy + alto, myPaint);
        canvas.drawLine((ancho + espaciox) - (barrax / 2), alto + espacioy, ancho + espaciox, espacioy + alto, myPaint);

        canvas.drawLine(espaciox, espacioy, espaciox, espacioy + (barrax / 2), myPaint);
        canvas.drawLine(espaciox, (alto + espacioy) - (barrax / 2), espaciox, alto + espacioy, myPaint);

        canvas.drawLine(ancho + espaciox, espacioy, ancho + espaciox, espacioy + (barrax / 2), myPaint);
        canvas.drawLine(ancho + espaciox, (alto + espacioy) - (barrax / 2), ancho + espaciox, espacioy + alto, myPaint);

        /*canvas.drawLine(espaciox, espacioy, ancho + espaciox, espacioy, myPaint); //linea superior
        canvas.drawLine(espaciox, alto + espacioy, ancho + espaciox, espacioy + alto, myPaint); //linea inferior

        canvas.drawLine(espaciox, espacioy, espaciox, alto + espacioy, myPaint); //linea izquierda
        canvas.drawLine(ancho + espaciox, espacioy, ancho + espaciox, espacioy + alto, myPaint); //linea deracha
        */Log.i("Draw", "Rectangle draw");
    }
}
