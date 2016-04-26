package com.example.number;


import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.ByteArrayBuffer;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class FileDownLoadAsyncTask extends
		AsyncTask<ImageView, Integer, Bitmap> {

	// ͼƬ���ص�ַ
	private String url = "http://img0.bdstatic.com/img/image/shouye/leimu/mingxing.jpg";
	private Context context;
	private ProgressDialog pd;
	private ImageView image;
	private int width = 150;
	private int height = 150;

	public FileDownLoadAsyncTask(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		pd = new ProgressDialog(context);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("������....");
		pd.setCancelable(false);
		pd.show();
	}

	/**
	 * ����ͼƬ������ָ���߶ȺͿ��ѹ��
	 */
	@Override
	protected Bitmap doInBackground(ImageView... params) {
		this.image = params[0];
		Bitmap bitmap = null;
		HttpClient httpClient = new DefaultHttpClient();
		try {
			httpClient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = httpResponse.getEntity();
				final long size = entity.getContentLength();
				CountingInputStream cis = new CountingInputStream(
						entity.getContent(), new ProgressListener() {

							@Override
							public void transferred(long transferedBytes) {
								Log.i("FileDownLoadAsyncTask", "���ֽ�����" + size
										+ " �������ֽ�����" + transferedBytes);
								publishProgress((int) (100 * transferedBytes / size));
							}
						});
				// �轫Inputstreamת��Ϊbyte���飬�Ա�decodeByteArray��
				// ��ֱ��ʹ��decodeStream�Ὣstream�ƻ���Ȼ��ڶ���decodeStreamʱ�������SkImageDecoder::Factory
				// returned null����
				// ���Թ�����õ�Inputstreamת��ΪBufferedInputStream��Ȼ��ʹ��mark��reset������������������û�ɹ�����֪��Ϊɶ������ɹ��ĸ�λ��֪
				byte[] byteIn = toByteArray(cis, (int) size);
				BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
				// ��һ��decodeʱ��������inJustDecodeBounds����Ϊtrue,����ϵͳ�ͻ�ֻ��ȡ����ͼƬ�����Զ�������ռ䣬�������Դ洢��Options��
				bmpFactoryOptions.inJustDecodeBounds = true;
				// ��һ��decode����ȡͼƬ�߿�ȵ�����
				BitmapFactory.decodeByteArray(byteIn, 0, byteIn.length,
						bmpFactoryOptions);
				// ������ʾ�ؼ���С��ȡѹ�����ʣ���Ч����OOM
				int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight
						/ height);
				int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth
						/ width);
				if (heightRatio > 1 && widthRatio > 1) {
					bmpFactoryOptions.inSampleSize = heightRatio > widthRatio ? heightRatio
							: widthRatio;
				}
				// �ڶ���decodeʱ��������inJustDecodeBounds����Ϊfasle,ϵͳ�Ż���ݴ����BitmapFactory.Options������ѹ��ͼƬ������
				bmpFactoryOptions.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeByteArray(byteIn, 0,
						byteIn.length, bmpFactoryOptions);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null && httpClient.getConnectionManager() != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return bitmap;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		pd.setProgress((int) (progress[0]));
	}

	@Override
	protected void onPostExecute(Bitmap bm) {
		pd.dismiss();
		if (bm != null) {
			image.setImageBitmap(bm);
		} else {
			Toast.makeText(context, "ͼƬ����ʧ��", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * InputStreamת��ΪByte����
	 * 
	 * @param instream
	 * @param contentLength
	 * @return
	 * @throws IOException
	 */
	public byte[] toByteArray(InputStream instream, int contentLength)
			throws IOException {
		if (instream == null) {
			return null;
		}
		try {
			if (contentLength < 0) {
				contentLength = 4096;
			}
			final ByteArrayBuffer buffer = new ByteArrayBuffer(contentLength);
			final byte[] tmp = new byte[4096];
			int l;
			while ((l = instream.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			return buffer.toByteArray();
		} finally {
			instream.close();
		}
	}

}
