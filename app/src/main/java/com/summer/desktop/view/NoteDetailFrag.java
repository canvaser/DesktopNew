package com.summer.desktop.view;

//by summer on 2017-05-24.

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.summer.desktop.R;
import com.summer.desktop.base.BaseFrag;
import com.summer.desktop.bean.gson.GsonNoteBean;
import com.summer.desktop.bean.gson.ImageNote;
import com.summer.desktop.bean.gson.NoteDetail;
import com.summer.desktop.bean.gson.TxtNote;
import com.summer.desktop.util.IntentUtil;
import com.summer.desktop.util.LogUtil;
import com.summer.desktop.util.SPUtil;
import com.summer.desktop.util.ViewCreater;

import java.util.ArrayList;

public class NoteDetailFrag extends BaseFrag implements View.OnLongClickListener {


    Handler handler = new Handler();

    @BindView(R.id.txtroot)
    LinearLayout root;

    @BindView(R.id.bmb)
    BoomMenuButton bmb;

    EditText ed;

    Gson gson = new Gson();

    GsonNoteBean bean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_txt, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        GsonNoteBean gsonNoteBean = new GsonNoteBean();
        gsonNoteBean.setData(new ArrayList<NoteDetail>());
        TxtNote txtNote1 = new TxtNote("作为开源代码库以及版本控制系统，Github拥有超过900万开发者用户。随着越来越多的应用程序转移到了云上，Github已经成为了管理软件开发以及发现已有代码的首选方法。\n" +
                "如前所述，作为一个分布式的版本控制系统，在Git中并不存在主库这样的");
        TxtNote txtNote2 = new TxtNote("GitHub可以托管各种git库，并提供一个web界面，但与其它像 SourceForge或Google Code这样的服务不同，GitHub的独特卖点在于从另外一个项目进行分支的简易性。为一个项目贡献代码非常简单：首先点击项目站点的“fork”的按钮，然后将代码检出并将修改加入到刚才分出的代码库中，最后通过内建的“pull request”机制向项目负责人申请代码合并。已经有人将GitHub称为代码玩家的MySpace。");
        TxtNote txtNote3 = new TxtNote("在GitHub进行分支就像在Myspace（或Facebook…）进行交友一样，在社会关系图的节点中不断的连线。\n" +
                "GitHub项目本身自然而然的也在GitHub上进行托管，只不过在一个私\n" +
                "有的，公共视图不可见的库中。开源项目可以免费托管，但私有库则并不如此。Chris Wanstrath，GitHub的开发者之一，肯定了通过付费的私有库来在财务上支持免费库的托管这一计划。\n" +
                "是的，我们正是这么计划的。通过与客户的接洽，开发FamSpam，甚至");
        TxtNote txtNote4 = new TxtNote("Chris Wanstrath还向记者分享了关于GitHub的一些内幕信息︰\n" +
                "GitHub主要用Rails实现。我们在进行的post-commit集成小应用完全使用Merb编写。我们使用了Python的Pygments来做格式高亮显示，另外，还用了Ara T. Howard's Bj加上一些Ruby脚本来做我们的排队系统。当然，我们用了Ruby Grit库来和Git进行交互。");
        TxtNote txtNote5 = new TxtNote("GitHub已经有了一组引人注目的特性，除了命令式的库浏览器和一个项目Wiki，GitHub甚至还包括了一个GitHub gem，以使通过shell方式使用GitHub更为方便。更多的未来特性已经在计划中︰\n" +
                "许多人都希望能有一个条目系统，因此一个简单的条目系统已经在开发中。此外，正如我前面所言，我们尚在进行RubyGems服务器和一些之前留出的post-commit钩子方面的工作。如果你不能或就是不想托管一个你自己的守护进程，你可以使用我们所提供的。\n" +
                "我们还在开发一些特性来帮助公司在使用Github时可以停留在sync之上。");
        TxtNote txtNote6 = new TxtNote("最后，我们也在进行API发布方面的工作。我们很快就会发布一些只读性的API，随後是一些很强大的“写”集成。你可以使用API将新的事件发布到新闻feed中，发消息和做其他许多很酷的事情。\n" +
                "GitHub尚未设定官方版本的发布日期，不过估计在三月底（GitHub已经上线，但只能通过邀请注册）。更多关于GitHub的信息可以参见GitHub官方网站或GitHub博客。通过GitHub进行代码管理的开源项目列表也已经可以查阅。");
        TxtNote txtNote7 = new TxtNote("GitHub 使用 git 分布式版本控制系统，而 git 最初是 Linux Torvalds 为帮助Linux开发而创造的，它针对的是 Linux 平台，因此 git 和 Windows 从来不是最好的朋友，因为它一点也不像 Windows。GitHub 发布了GitHub for Windows，为 Windows 平台开发者提供了一个易于使用的 Git 图形客户端。\n" +
                "GitHub For Windows\n" +
                "GitHub For Windows\n" +
                "GitHub for Windows 是一个 Metro 风格应用程序，集成了自包含版本的 Git，bash 命令行 shell，PowerShell 的 posh-git 扩展。GitHub 为 Windows 用户提供了一个基本的图形前端去处理大部分常用版本控制任务，可以创建版本库，向本地版本库递交补丁，在本地和远程版本库之间同步。微软也通过CodePlex向开发者");
        TxtNote txtNote8 = new TxtNote("全球最大的社交编程及代码托管网站GitHub以其开创性的新型软件开发方式并且能高效利用有限的资源通过自力更生实现公司盈利和300%的年收入增长成功的吸引知名风投机构Andreessen Horowitz一亿美金的投资。新的资金注入将帮助GitHub平台得到进一步的改进和扩展。\n" +
                "事实上，这不仅对首次接受外部投资的GitHub意义重");
        TxtNote txtNote9 = new TxtNote("GitHub上已自动配置的Mac笔记本电脑，一个工具，可以转换设置Linux或Windows机器。\n" +
                "　　BOXEN是GitHub的自动化工具，设置和配置的Mac笔记本电脑软件开发或其他类型的工作，正在使用他们的开发人员，律师，设计师，付货人，等。我们的想法是准备系统以自动方式和作为无差错尽可能用最少的干预工作。根据GitHub上，与一个新的开发机器上，他的Mac系统成立，并准备在30分钟内提交代码。\n" +
                "　　BOXEN的基础上收集了大量的几十个木偶模块，使设置的各种软件，如卡桑德拉，MongoDB中，Java软件中，Python和Ruby开发中，节点，JS，nginx的，Skype公司，甚至MINECRAFT。虽然机器上配备了一个预配置，每个用户都可以调整它的配置应有的作用。");
        ImageNote imageNote1 = new ImageNote("http://pic62.nipic.com/file/20150319/12632424_132215178296_2.jpg");
        ImageNote imageNote2 = new ImageNote("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        ImageNote imageNote3 = new ImageNote("http://pic41.nipic.com/20140518/18521768_133448822000_2.jpg");
        ImageNote imageNote4 = new ImageNote("http://pic41.nipic.com/20140518/18521768_133448822000_2.jpg");
        ImageNote imageNote5 = new ImageNote("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496332542&di=3860d7045eb5496063a9aa3885da297b&imgtype=jpg&er=1&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F17%2F94%2F90%2F45w58PIC7j6_1024.jpg");
        ImageNote imageNote6 = new ImageNote("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495737890290&di=ece26171e70b1e37092abcb6b2302526&imgtype=0&src=http%3A%2F%2Ffile06.16sucai.com%2F2016%2F0303%2F3d6c82d7321ca041ae5de6e868bda288.jpg");
        ImageNote imageNote7 = new ImageNote("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495737927507&di=7d1849ef67d0cecbb71a12f6f75d327f&imgtype=0&src=http%3A%2F%2Fimg0.cache.hxsd.com%2Fhxsdmy%2Fgallery%2F2013%2F01%2F88%2F48%2F61%2F03%2F28%2F134037443%2F134037443_8.jpg");
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote1)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.IMAGE,gson.toJson(imageNote7)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote2)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.IMAGE,gson.toJson(imageNote2)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.IMAGE,gson.toJson(imageNote3)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote3)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.IMAGE,gson.toJson(imageNote6)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote4)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote5)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.IMAGE,gson.toJson(imageNote5)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote6)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote7)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote8)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.IMAGE,gson.toJson(imageNote4)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.TXT,gson.toJson(txtNote9)));
        gsonNoteBean.getData().add(new NoteDetail(NoteDetail.IMAGE,gson.toJson(imageNote1)));

        String ss = SPUtil.getInstance().init(getContext()).getStr("desktop");
        if (ss.equals("")) {
            SPUtil.getInstance().saveStr("desktop", gson.toJson(gsonNoteBean));
        }
        ss = SPUtil.getInstance().getStr("desktop");
        bean = gson.fromJson(ss,GsonNoteBean.class);
        LogUtil.E(ss);

        root.addView(ViewCreater.create(getContext(), bean.getData()), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ViewGroup view1 = (ViewGroup) root.getChildAt(0);
        for (int i = 0; i < view1.getChildCount(); i++) {
            view1.getChildAt(i).setOnLongClickListener(NoteDetailFrag.this);
            if (view1.getChildAt(i) instanceof EditText) {
                final EditText editText = (EditText) view1.getChildAt(i);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            ed = (EditText) v;
                        }else{
                            int i= (int) v.getTag(R.id.position);
                            EditText editText = (EditText) v;
                            TxtNote txtNote = gson.fromJson(bean.getData().get(i).getData(),TxtNote.class);
                            txtNote.setTxt(editText.getText().toString());
                            bean.getData().get(i).setData(gson.toJson(txtNote));
                        }
                    }
                });

            }
        }

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(getActivity(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                            IntentUtil.getInstance().photoShowFromphone(NoteDetailFrag.this, 0);
                        }
                    });
            builder.rotateText(true);
            builder.rotateImage(true);
            bmb.addBuilder(builder);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        BoomFrag boomFrag = new BoomFrag();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.root, boomFrag);
        transaction.commit();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SPUtil.getInstance().saveStr("desktop", gson.toJson(bean));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (ed != null && bean.getData()!=null && bean.getData().size() > 0) {
            int i = (int) ed.getTag(R.id.position);
            bean.getData().add(i,new NoteDetail(NoteDetail.IMAGE,gson.toJson(new ImageNote(data.getDataString()))));
        }



        root.removeAllViews();
        root.addView(ViewCreater.create(getContext(), bean.getData()), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ViewGroup view1 = (ViewGroup) root.getChildAt(0);
        for (int i = 0; i < view1.getChildCount(); i++) {
            view1.getChildAt(i).setOnLongClickListener(NoteDetailFrag.this);
            if (view1.getChildAt(i) instanceof EditText) {
                EditText editText = (EditText) view1.getChildAt(i);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            ed = (EditText) v;
                        }
                    }
                });

            }
        }
    }
}
