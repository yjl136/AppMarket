package razerdp.basepopup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import razerdp.basepopup.fragment.CommentPopupFrag;
import razerdp.basepopup.fragment.DialogPopupFrag;
import razerdp.basepopup.fragment.InputPopupFrag;
import razerdp.basepopup.fragment.ListPopupFrag;
import razerdp.basepopup.fragment.MenuPopupFrag;
import razerdp.basepopup.fragment.ScalePopupFrag;
import razerdp.basepopup.fragment.SlideFromBottomPopupFrag;
import razerdp.basepopup.popup.ListPopup;
public class DemoActivity extends FragmentActivity {
    private FragmentManager mFragmentManager;
    private ScalePopupFrag mNormalPopupFrag;
    private SlideFromBottomPopupFrag mSlideFromBottomPopupFrag;
    private CommentPopupFrag mCommentPopupFrag;
    private InputPopupFrag mInputPopupFrag;
    private ListPopupFrag mListPopupFrag;
    private MenuPopupFrag mMenuPopupFrag;
    private DialogPopupFrag mDialogPopupFrag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mFragmentManager=getSupportFragmentManager();

        mNormalPopupFrag=new ScalePopupFrag();
        mSlideFromBottomPopupFrag=new SlideFromBottomPopupFrag();
        mCommentPopupFrag=new CommentPopupFrag();
        mInputPopupFrag=new InputPopupFrag();
        mListPopupFrag=new ListPopupFrag();
        mMenuPopupFrag=new MenuPopupFrag();
        mDialogPopupFrag=new DialogPopupFrag();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.id_scale_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent,mNormalPopupFrag).commit();
                break;
            case R.id.id_slide_from_bottom_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent,mSlideFromBottomPopupFrag).commit();
                break;
            case R.id.id_comment_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent,mCommentPopupFrag).commit();
                break;
            case R.id.id_input_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent,mInputPopupFrag).commit();
                break;
            case R.id.id_list_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent,mListPopupFrag).commit();
                break;
            case R.id.id_menu_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent,mMenuPopupFrag).commit();
                break;
            case R.id.id_dialog_popup:
                mFragmentManager.beginTransaction().replace(R.id.parent,mDialogPopupFrag).commit();
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
