package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.CheckoutAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.OrderViewModel;

public class OrderDetailFragment extends Fragment {

    public native int sumIntArr(int[] arr);

    static {
        System.loadLibrary("fourteencoffee");
    }

    private OrderViewModel orderViewModel;

    public static OrderDetailFragment newInstance() {
        return new OrderDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderViewModel = ViewModelProviders.of(getActivity()).get(OrderViewModel.class);
        final Order activeOrder = orderViewModel.orders.getValue().get(orderViewModel.activeOrderDetailIndex);
        List<Cart> activeOrderCart = activeOrder.getOrderCarts();

        CheckoutAdapter checkoutAdapter = new CheckoutAdapter();
        checkoutAdapter.setCartList(activeOrderCart);
        RecyclerView orderDetailRecyclerView = getView().findViewById(R.id.order_detail_recycler_view);
        orderDetailRecyclerView.setAdapter(checkoutAdapter);
        orderDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TextView addressText = getView().findViewById(R.id.order_detail_address);
        addressText.setText(activeOrder.getAddress());

        orderViewModel.getAllOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                Order activeOrder = orders.get(orderViewModel.activeOrderDetailIndex);
                TextView statusText = getView().findViewById(R.id.order_detail_status);
                statusText.setText(R.string.status);
                String statusString = statusText.getText().toString();
                statusText.setText(activeOrder.isDone() ? R.string.done : R.string.process);
                statusText.setText(statusString + " : " + statusText.getText());
            }
        });

        TextView totalPriceText = getView().findViewById(R.id.order_detail_total_price);
        int sum = sumIntArr(activeOrder.getArrOrderDetail());
        totalPriceText.setText(totalPriceText.getText() + " : " + sum);

        Button receiptButton = getView().findViewById(R.id.order_detail_receipt_button);
        receiptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), R.string.unavailable, Toast.LENGTH_SHORT).show();
//                failed attempt on FileProvider
//                Document doc = new Document();
//                String path = "";
//
//                try {
//                    path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dir";
//
//                    File dir = new File(path);
//                    if(!dir.exists())
//                        dir.mkdirs();
//
//                    File file = new File(dir, "newFile.pdf");
//                    FileOutputStream fOut = new FileOutputStream(file);
//
//                    PdfWriter.getInstance(doc, fOut);
//
//                    //open the document
//                    doc.open();
//
//                    Paragraph p1 = new Paragraph(activeOrder.getDate());
//                    Font paraFont= new Font(Font.FontFamily.COURIER);
//                    p1.setAlignment(Paragraph.ALIGN_CENTER);
//                    p1.setFont(paraFont);
//
//                    //add paragraph to document
//                    doc.add(p1);
//
//                } catch (DocumentException de) {
//                    Log.e("PDFCreator", "DocumentException:" + de);
//                } catch (IOException e) {
//                    Log.e("PDFCreator", "ioException:" + e);
//                }
//                finally {
//                    doc.close();
//                }
//
//                File pdfFile = new File(path);
//                Uri uri = Uri.fromFile(pdfFile);
//
//                // Setting the intent for pdf reader
//                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//                pdfIntent.setDataAndType(uri, "application/pdf");
//                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                try {
//                    startActivity(pdfIntent);
//                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(getActivity(), "Can't read pdf file", Toast.LENGTH_SHORT).show();
//                }
            }
//                Order activeOrder = orderViewModel.orders.getValue().get(orderViewModel.activeOrderDetailIndex);
//                PdfDocument document = new PdfDocument();
//                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
//                PdfDocument.Page page = document.startPage(pageInfo);
//                Canvas canvas = page.getCanvas();
//                Paint paint = new Paint();
//                paint.setColor(Color.BLACK);
//                canvas.drawText(activeOrder.getDate(), 80, 50, paint);
//                document.finishPage(page);
//
//                String directory_path = getContext().getFilesDir() + "/mypdf/";
//                File file = new File(directory_path);
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//                String targetPdf = directory_path + activeOrder.getDate() + " " + activeOrder.getId() + ".pdf";
//                File filePath = new File(targetPdf);
//                try {
//                    document.writeTo(getContext().openFileOutput(filePath.getName(), Context.MODE_PRIVATE));
//                    Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();
//
//                    File pdfFile = new File(targetPdf);
//                    Uri path = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName()+".provider", pdfFile);
//
//                    // Setting the intent for pdf reader
//                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//                    pdfIntent.setDataAndType(path, "application/pdf");
//                    pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                    try {
//                        startActivity(pdfIntent);
//                    } catch (ActivityNotFoundException e) {
//                        Toast.makeText(getActivity(), "Can't read pdf file", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (IOException e) {
//                    Log.e("main", "error "+e.toString());
//                    Toast.makeText(getActivity(), "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
//                }
//                // close the document
//                document.close();
//            }
        });

        Button backButton = getView().findViewById(R.id.order_detail_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}
