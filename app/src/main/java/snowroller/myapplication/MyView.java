package snowroller.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Martin on 2018-01-10.
 */

public class MyView extends View implements View.OnTouchListener {
    private Paint snakePaint;
    private Paint applePaint;
    private Paint textPaint;

    public Point getApple() {
        return apple;
    }

    private Point apple;
    private Random random;
    private LinkedList<Point> snake = new LinkedList<>();
    private int width;
    private int height;
    int nextx;
    int nexty;
    int vx = 1;
    int vy = 0;
    int length;

    public void setActive(boolean active) {
        if( this.active != active) {
            this.active = active;
            if (active)
                invalidate();
        }
    }

    private boolean active;

    public MyView(Context context) {
        super(context);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        width=dm.widthPixels;
        height=dm.heightPixels;

        nextx = width/80;
        nexty = height/80;

         snakePaint= new Paint();
         snakePaint.setColor(Color.GREEN);
         applePaint = new Paint();
         applePaint.setColor(Color.RED);
         textPaint = new Paint();
         textPaint.setColor(Color.BLACK);
         textPaint.setTextSize(24.0f);

         snake.add(new Point(nextx++,nexty));
         snake.add(new Point(nextx++,nexty));
         snake.add(new Point(nextx++,nexty));
         snake.add(new Point(nextx++,nexty));
         snake.add(new Point(nextx,nexty));

         length = 5;

         random = new Random();
         apple = new Point(random.nextInt(width/40),random.nextInt(height/40));

         this.setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int h = getHeight();
        int w = getWidth();

        if( active )
            updateSnake();

        for ( Point p: snake ) {
            canvas.drawCircle(p.x * 40, p.y * 40, 20.0f, snakePaint);
        }

        canvas.drawCircle(apple.x * 40, apple.y * 40, 20.0f, applePaint);

        canvas.drawText("Segments: " + length, 40.0f,40.0f,textPaint );

        if( active )
            postDelayed(this::invalidate,250);
    }

    private void updateSnake() {
        nextx += vx;
        nexty += vy;

        Point newpoint = new Point(nextx, nexty);
        snake.add(newpoint);

        if (nextx < 0)
            nextx = width / 40;
        if (nexty < 0)
            nexty = height / 40;
        if (nextx > width / 40)
            nextx = 0;
        if (nexty > height / 40)
            nexty = 0;

        if (apple.x == newpoint.x && apple.y == newpoint.y){
            apple = new Point(random.nextInt(width / 40), random.nextInt(height / 40));
        }
        else
            snake.pollFirst();

        //If we eat ourself, cut snake at intersection point
        Point head = snake.getLast();
        for( int i = snake.size()-4; i > 0; i--) {
            //Can never reach the closest segments.. start at 4th segment behind the head
            Point body = snake.get(i);
            if( head.x == body.x && head.y == body.y)
            {
                //sublist(from, to) Updates the original list. To is exclusive, from inclusive.
                snake.subList(0, i+1).clear();
                break;
            }
        }

        length = snake.size();
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Restart if touched and we are paused
        if( !active ) {
            setActive(true);
            return true;
        }

        if( event.getAction() == MotionEvent.ACTION_DOWN )
        {
            if( vy == 0 )
            {
                    if (event.getY()/40 < nexty)
                        vy = -1;
                    else
                        vy = 1;
                vx = 0;
            }
            else if( vx == 0 )
            {
                    if (event.getX()/40 < nextx)
                        vx = -1;
                    else
                        vx = 1;

                vy = 0;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point p : snake) {
            sb.append(p.x).append(',').append(p.y).append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void setSnake(String snakeData) {
        if (snakeData == null || snakeData.isEmpty())
            return;

        String[] arr = snakeData.split(",");
        snake.clear();

        for (int i = 0; i < arr.length; ) {
            int x = Integer.parseInt(arr[i++]);
            int y = Integer.parseInt(arr[i++]);
            snake.add(new Point(x, y));
        }
        Point head = snake.getLast();
        Point headminusone = snake.get(snake.size() - 2);

        length = snake.size();

        nextx = head.x;
        nexty = head.y;

        vx = head.x - headminusone.x;
        vy = head.y - headminusone.y;

        if (vx < -1 || vx > 1 || vy < -1 || vy > 1) {
            //Todo: Convert to intervall -1,0,1 rare error when head is on one side of screen and headminusone on the other side
        }

        //Don't start yet
        setActive(false);
    }

    public void setApple(Point apple) {
        this.apple = apple;
    }
}

