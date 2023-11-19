package com.swe.buddybud.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.swe.buddybud.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BoardDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoardDetailFragment newInstance(String param1, String param2) {
        BoardDetailFragment fragment = new BoardDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_board_detail_1, container, false);


        ImageButton scrap_1_Button = rootView.findViewById(R.id.scrap_1_Button);
        final TextView[] scrap_1_Count = {rootView.findViewById(R.id.scrap_1_Count)};

        String scrap_1_Count_Text = scrap_1_Count[0].getText().toString();
        final int[] scrap_1_Count_int = {Integer.parseInt(scrap_1_Count_Text)};

        final boolean[] isScrapped_1 = { true };
        scrap_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isScrapped_1[0]) {
                    scrap_1_Button.setImageResource(R.drawable.scrapping);
                    scrap_1_Count_int[0]--;
                    scrap_1_Count[0].setText(String.valueOf(scrap_1_Count_int[0]));
                    isScrapped_1[0] = false;
                } else {
                    scrap_1_Button.setImageResource(R.drawable.scrapped);
                    scrap_1_Count_int[0]++;
                    scrap_1_Count[0].setText(String.valueOf(scrap_1_Count_int[0]));
                    isScrapped_1[0] = true;
                }
            }
        });

        ImageButton like_1_Button = rootView.findViewById(R.id.like_1_Button);
        final TextView[] like_1_Count = {rootView.findViewById(R.id.like_1_Count)};

        String like_1_Count_Text = like_1_Count[0].getText().toString();
        final int[] like_1_Count_int = {Integer.parseInt(like_1_Count_Text)};

        final boolean[] isLiked_1 = { true };
        like_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked_1[0]) {
                    like_1_Button.setImageResource(R.drawable.liking);
                    like_1_Count_int[0]--;
                    like_1_Count[0].setText(String.valueOf(like_1_Count_int[0]));
                    isLiked_1[0] = false;
                } else {
                    like_1_Button.setImageResource(R.drawable.liked);
                    like_1_Count_int[0]++;
                    like_1_Count[0].setText(String.valueOf(like_1_Count_int[0]));
                    isLiked_1[0] = true;
                }
            }
        });

        ImageButton translate_1_Button = rootView.findViewById(R.id.translate_1_Button);
        TextView post_1_Author = rootView.findViewById(R.id.post_1_Author);
        TextView post_1_Title = rootView.findViewById(R.id.post_1_Title);
        TextView post_1_Content = rootView.findViewById(R.id.post_1_Content);
        ImageView frame_1_Shadow = rootView.findViewById(R.id.frame_1_Shadow);
        ImageButton link_1_Button = rootView.findViewById(R.id.link_1_Button);

        final boolean[] isTranslated_1 = { false };
        translate_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTranslated_1[0]) {
                    post_1_Author.setText("성균관대학교 공지사항");
                    post_1_Title.setText("[채용/모집] [연장] 성균관대학교 SW융합대학 소프트웨어학과 융합보안트랙 담당 행정직원 모집(~10/15일, 일)");
                    post_1_Content.setText("성균관대학교 SW융합대학 소프트웨어학과 융합보안트랙에서 학사 및 연구과제 행정업무를 담당할 직원을 아래와 같이 모집하니 관심 있는 분의 많은 지원 바랍니다.\n\n 1. 직종 및 모집인원: 행정직원 1명\n 2. 근무부서 및 담당업무\n     가. 근무처: SW융합대학 소프트웨어학과\n    나. 담당업무: 융합보안트랙 학사 및 연구과제 행정업무\n 3. 근로조건\n    가. 계약기간 : 1년 단위 계약(매년 근무평가에 따라 연장 여부 결정)\n   나. 근무장소: 성균관대학교 자연과학캠퍼스(수원)\n   다. 근무시간: 월~금, 09:00~17:30\n    라. 급    여: 월 270~300만원\n 4. 지원자격\n    가. 학사 학위 이상 소지자\n    나. 우대사항\n       - 대학 학사행정 및 연구비 관리 업무 경력 1년 이상\n       - MS Office(Excel, Powerpoint), 한글 등 사무용 컴퓨터 프로그램 사용 가능자\n       - 홈페이지 제작 및 관리 경험자 우대\n       - 즉시 근무 가능자 선호\n ");
                    frame_1_Shadow.setMinimumHeight(2130);
                    like_1_Button.setTranslationY(1952);
                    like_1_Count[0].setTranslationY(2007);
                    link_1_Button.setTranslationY(1955);
                    translate_1_Button.setTranslationY(1952);
                    isTranslated_1[0] = false;
                } else {
                    post_1_Author.setText("SKKU Notice");
                    post_1_Title.setText("[Recruit] [Extension] Recruiting Administrative Staff for the Department of Software Convergence Security Track, SKKU College of Software Convergence (Until October 15th, Sun)");
                    post_1_Content.setText("Sungkyunkwan University College of Software Convergence, Department of Software Engineering Convergence Security Track, is recruiting staff to handle administrative and research project tasks as follows.\n" +
                            "\t1.\tJob Title and Number of Openings: Administrative Staff, 1 position\n" +
                            "\t2.\tDepartment and Responsibilities\n" +
                            "          A. Department: Department of Software Engineering Convergence, College of Software Convergence\n" +
                            "          B. Responsibilities: Administrative work for the Convergence Security Track's academic and research projects\n" +
                            "\t4.\tEmployment Conditions\n" +
                            "          A. Contract Period: Annual contract (Renewal based on annual performance evaluation)\n" +
                            "          B. Workplace: Sungkyunkwan University, Natural Science Campus (Suwon)\n" +
                            "          C. Working Hours: Monday to Friday, 09:00 AM to 05:30 PM\n" +
                            "          D. Salary: Monthly KRW 2,700,000 to 3,000,000\n" +
                            "\t5.\tQualifications\n" +
                            "          A. Minimum of a bachelor's degree\n" +
                            "          B. Preferred Qualifications\n" +
                            "              - 1 year or more of experience in university academic administration and research fund management\n" +
                            "              - Proficiency in MS Office (Excel, PowerPoint), Korean word processing, and other office computer programs\n" +
                            "              - Preferred experience in website creation and management\n" +
                            "              - Preferably available for immediate employment");
                    frame_1_Shadow.setMinimumHeight(2500);
                    like_1_Button.setTranslationY(2322);
                    like_1_Count[0].setTranslationY(2377);
                    link_1_Button.setTranslationY(2325);
                    translate_1_Button.setTranslationY(2322);
                    isTranslated_1[0] = true;
                }
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton back_1_Button = view.findViewById(R.id.back_1_Button);
        back_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BoardFragment();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.board_detail_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}