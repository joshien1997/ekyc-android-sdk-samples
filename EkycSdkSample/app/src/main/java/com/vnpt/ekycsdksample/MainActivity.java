package com.vnpt.ekycsdksample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vnptit.idg.sdk.activity.VnptIdentityActivity;
import com.vnptit.idg.sdk.utils.SDKEnum;

import static com.vnptit.idg.sdk.utils.KeyIntentConstants.ACCESS_TOKEN;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CAMERA_FOR_PORTRAIT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHECK_LIVENESS_CARD;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHECK_MASKED_FACE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.LIVENESS_ADVANCED;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.SELECT_DOCUMENT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.SHOW_DIALOG_SUPPORT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.SHOW_RESULT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.SHOW_SWITCH;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.TOKEN_ID;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.TOKEN_KEY;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.VERSION_SDK;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.COMPARE_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.FRONT_IMAGE;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.INFO_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_CARD_FRONT_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_CARD_REAR_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.MASKED_FACE_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.PORTRAIT_IMAGE;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.REAR_IMAGE;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SCAN = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VnptIdentityActivity.class);
                intent.putExtra(ACCESS_TOKEN,"put_access_token_here");
                intent.putExtra(TOKEN_ID,"put_token_id_here");
                intent.putExtra(TOKEN_KEY,"put_token_key_here");
                //nếu không muốn sử dụng màn hình chọn loại giấy tờ có sẵn,
                //bạn có thể truyền vào giá trị của loại giấy tờ bạn muốn bằng key DOCUMENT_TYPE với value thuộc enum DocumentTypeEnum
                intent.putExtra(SELECT_DOCUMENT, true);
                intent.putExtra(VERSION_SDK, SDKEnum.VersionSDKEnum.ADVANCED.getValue());
                intent.putExtra(SHOW_SWITCH, false);
                intent.putExtra(SHOW_DIALOG_SUPPORT, true);
                intent.putExtra(SHOW_RESULT, true);
                intent.putExtra(CAMERA_FOR_PORTRAIT, SDKEnum.CameraTypeEnum.FRONT.getValue());
                intent.putExtra(CHECK_MASKED_FACE, true);
                intent.putExtra(CHECK_LIVENESS_CARD, true);
                intent.putExtra(LIVENESS_ADVANCED, true);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_SCAN) {
                //Nhận kết quả và xử lý ở đây
                String strDataInfo = data.getStringExtra(INFO_RESULT);
                String strDataCompare = data.getStringExtra(COMPARE_RESULT);
                String strDataLiveness = data.getStringExtra(LIVENESS_RESULT);
                String strDataMaskedFace = data.getStringExtra(MASKED_FACE_RESULT);
                String strDataLivenessCardFront = data.getStringExtra(LIVENESS_CARD_FRONT_RESULT);
                String strDataLivenessCardRear = data.getStringExtra(LIVENESS_CARD_REAR_RESULT);
                String imageFront = data.getStringExtra(FRONT_IMAGE);
                String imageRear = data.getStringExtra(REAR_IMAGE);
                String imagePortrait = data.getStringExtra(PORTRAIT_IMAGE);
            }
        }
    }
}