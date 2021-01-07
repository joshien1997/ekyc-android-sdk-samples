**QUICK START**<br/>

Support **minSdkVersion 16** <br/>
**Integration Steps**
1. Adding the SDK to your project
- Download *zip file* and then extract to **android_ekyc_sdk_v*.aar** file.
- Open project in Android Studio. Switch Project folder view as Project. 
- Right click to the root folder, choose **New -> Module -> Import .JAR/.AAR Package**. Browser to the aar file. Look and remember "Subproject name" and then click **Finish**.
- Add the following set of lines to your **app/build.gradle**:

	    android {
	        ...
	        aaptOptions {
	            noCompress "tflite"
	            noCompress "lite"
	        }
	    }
	    
	    dependencies {
	        implementation fileTree(dir: 'libs', include: ['*.jar'])
	        //Add some following library if it does not already exist. 
	        //If your project still use "Android Support", please add the same type library.
	        implementation 'androidx.appcompat:appcompat:1.1.0'
	        implementation 'com.google.android.material:material:1.3.0-alpha02'
	        implementation 'androidx.exifinterface:exifinterface:1.2.0'
	        implementation 'com.google.code.gson:gson:2.8.2'
	        
	        //add this to implement sdk module
	        implementation project(':<subproject name>')
	    }
- Note: If you run app on Android 10, add the following line to your tag <application> in Android Manifest to fix bugs about storage.
	android:requestLegacyExternalStorage="true"
2. Start example full flow

- Call to start full flow:

		Intent intent = new Intent(this, VnptIdentityActivity.class);
		intent.putExtra(ACCESS_TOKEN, "access token");
		intent.putExtra(TOKEN_ID, "token id");
		intent.putExtra(TOKEN_KEY, "token key");
		intent.putExtra(DOCUMENT_TYPE, SDKEnum.DocumentTypeEnum.IDENTITY_CARD.getValue());
		intent.putExtra(SELECT_DOCUMENT, false);
		intent.putExtra(VERSION_SDK, SDKEnum.VersionSDKEnum.ADVANCED.getValue());
		intent.putExtra(SHOW_RESULT, true);
		startActivityForResult(intent, REQUEST_CODE_SCAN);
	    //you can check the documentation or class key KeyIntentContants if you want to execute other flow or validation.

- Call to receive response:

		@Override
		protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		    if (resultCode == RESULT_OK) {
		        if (requestCode == REQUEST_CODE_SCAN) {
		            //read data ocr document
		            String strDataInfo = data.getStringExtra(INFO_RESULT);
		            BaseResultResponse<IdentityCard> responseInfo = gson.fromJson(responseInfo, new TypeToken<BaseResultResponse<IdentityCard>>() {
		            }.getType());
		            //read data compare face selfie with document
		            String strDataCompare = data.getStringExtra(COMPARE_RESULT);
		            BaseResultResponse<CompareFaceObject> responseCompare = gson.fromJson(strDataCompare, new TypeToken<BaseResultResponse<CompareFaceObject>>() {
		            }.getType());
		            //path to image
		            String pathImageFront = data.getStringExtra(FRONT_IMAGE);
		            String pathImageRear = data.getStringExtra(REAR_IMAGE);
		            String pathImagePortrait = data.getStringExtra(PORTRAIT_IMAGE);
					//you can check the documentation or class key KeyResultContants if you want to get more data.
		        }
		    }
		}
