package com.luacevedo.heartbaymax.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.luacevedo.heartbaymax.Constants;
import com.luacevedo.heartbaymax.R;
import com.luacevedo.heartbaymax.model.patient.PatientAttribute;
import com.luacevedo.heartbaymax.ui.activities.PatientPageActivity;
import com.luacevedo.heartbaymax.ui.views.PatientAttributeView;

import java.util.ArrayList;
import java.util.List;

public class PatientPageDataFragment extends BaseFragment {

    private LinearLayout patientContentLayout;
    private PatientPageActivity activity;
    private Constants.PatientStage patientStage;

    public static PatientPageDataFragment newInstance(Constants.PatientStage patientStage) {
        PatientPageDataFragment fragment = new PatientPageDataFragment();
        fragment.setPatientStage(patientStage);
        return fragment;
    }

    public void setPatientStage(Constants.PatientStage patientStage) {
        this.patientStage = patientStage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (PatientPageActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_page_data, container, false);
        activity = (PatientPageActivity) getActivity();
        setupViews(view);
        addPatientAttributes();
        return view;
    }

    private void setupViews(View view) {
        patientContentLayout = (LinearLayout) view.findViewById(R.id.patient_page_data_content);
    }

    private void addPatientAttributes() {
        List<PatientAttribute> essentialSymptomsList = new ArrayList<>();
        List<PatientAttribute> secondarySymptomsList = new ArrayList<>();
        List<PatientAttribute> preliminaryDiagnosisList = new ArrayList<>();
        for (PatientAttribute attribute : activity.getPatient().getAttributesMap().values()) {
            String root = attribute.getAttribute().getRootParent();
            switch (root) {
                case Constants.Patient.ESSENTIAL_SYMPTOMS:
                    essentialSymptomsList.add(attribute);
                    break;
                case Constants.Patient.SECONDARY_SYMPTOMS:
                    secondarySymptomsList.add(attribute);
                    break;
                default:
                    break;
            }
        }

        addValuesToLayout(essentialSymptomsList);
        addValuesToLayout(secondarySymptomsList);
        addValuesToLayout(preliminaryDiagnosisList);
    }

    private void addValuesToLayout(List<PatientAttribute> list) {
        for (PatientAttribute attribute : list) {
            PatientAttributeView viewAttribute = new PatientAttributeView(getActivity());
            viewAttribute.setData(attribute);
            patientContentLayout.addView(viewAttribute);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            slidePreviousFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
